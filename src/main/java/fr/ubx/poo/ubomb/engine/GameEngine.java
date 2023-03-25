/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.engine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.NonStaticObject;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Bomb;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.view.ImageResource;
import fr.ubx.poo.ubomb.view.sprite.Sprite;
import fr.ubx.poo.ubomb.view.sprite.SpriteFactory;
import fr.ubx.poo.ubomb.view.sprite.SpriteChara;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public final class GameEngine {

	private static AnimationTimer gameLoop;
	private final Game game;
	private final Player player;
	private final List<Sprite> sprites = new LinkedList<>();
	private final Set<Sprite> cleanUpSprites = new HashSet<>();
	private final Stage stage;
	private StatusBar statusBar;
	private Pane layer;
	private Input input;
	private int level;

	public GameEngine(Game game, final Stage stage) {
		this.stage = stage;
		stage.setResizable(false);
		this.game = game;
		this.level = game.getLevel();
		this.player = game.player();
		initialize();
		buildAndSetGameLoop();
	}

	private void initSprites() {
		// Create sprites
		for (var decor : game.grid().values()) {
			sprites.add(SpriteFactory.create(layer, decor));
			decor.setModified(true);
		}

		sprites.add(new SpriteChara(layer, player));

		// create monsters sprites
		for (var monster : game.getMonsters().getObjects(game.getLevel())) {
			sprites.add(new SpriteChara(layer, monster));
			monster.setModified(true);
		}

		// create boxes sprites
		for (var boxes : game.getBoxes().getObjects(game.getLevel())) {
			sprites.add(SpriteFactory.create(layer, boxes));
			boxes.setModified(true);
		}

		// create bombs sprites
		for (var bombs : game.getBombs().getObjects(game.getLevel())) {
			sprites.add(SpriteFactory.create(layer, bombs));
			bombs.setModified(true);
		}

	}

	private void initGroup() {
		layer = new Pane();
		Group root = new Group();
		int height = game.grid().height();
		int width = game.grid().width();
		int sceneWidth = width * ImageResource.SIZE;
		int sceneHeight = height * ImageResource.SIZE;
		Scene scene = new Scene(root, sceneWidth, (double) sceneHeight + StatusBar.height);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		stage.setScene(scene);
		stage.sizeToScene();
		stage.centerOnScreen();
		stage.hide();
		stage.show();
		input = new Input(scene);
		root.getChildren().add(layer);
		statusBar = new StatusBar(root, sceneWidth, sceneHeight, game);
	}

	private void initialize() {
		this.initGroup();
		this.initSprites();
	}

	private void changeLevel() {
		this.initGroup();
		sprites.forEach(Sprite::remove);
		sprites.clear();
		this.initSprites();
	}

	void buildAndSetGameLoop() {
		gameLoop = new AnimationTimer() {
			public void handle(long now) {
				// Check keyboard actions
				processInput();

				// Do actions
				update(now);
				checkCollision();
				checkExplosions();

				// Graphic update
				cleanupSprites();
				render();
				statusBar.update();
			}
		};
	}

	private int explodeThingsOnExplosion(Position pos, int sizeExplosion, Direction dir, int level) {
		NonStaticObject<Box> boxes = this.game.getBoxes();
		NonStaticObject<Monster> monsters = this.game.getMonsters();
		NonStaticObject<Bomb> bombs = this.game.getBombs();
		boolean stopped = false;
		for (int i = 0; i <= sizeExplosion; i++) {
			Decor decor = this.game.grid(level).get(pos);
			int indexBox = boxes.isThereObject(pos, level);
			int indexMonster = monsters.isThereObject(pos, level);
			int indexbomb = bombs.isThereObject(pos, level);
			if (this.game.getLevel() == level && player.getPosition().equals(pos)) {
				if (!player.isInvincible()) {
					player.addLives(-1);
					player.getInvincibilityTimer().start();
				}
			}
			if (indexMonster != -1) {
				Monster monster = monsters.getObjects(level).get(indexMonster);
				if (!monster.isInvincible()) {
					monster.addLives(-1);
					monster.getInvincibilityTimer().start();
				}
			}
			if (indexbomb != -1) {
				bombs.getObjects(level).get(indexbomb).explode();
			}
			if (decor != null && !decor.isDeleted()) {
				decor.explode();
				stopped = true;
			}
			if (indexBox != -1) {
				boxes.getObjects(level).get(indexBox).explode();
				stopped = true;
			}
			if (stopped)
				return i;
			pos = dir.nextPosition(pos);
		}
		return sizeExplosion;
	}

	private void checkExplosions() {
		for (int level = 0; level <= this.game.getLevels(); level++) {
			List<Bomb> bombs = this.game.getBombs().getObjects(level);
			for (Bomb bomb : bombs) {
				if (bomb.isExploded()) {
					Position src = bomb.getPosition();
					for (Direction direction : Direction.values()) {
						int exploSize = explodeThingsOnExplosion(bomb.getPosition(),
								bomb.getRangeExplosion(),
								direction, level);
						Position dst = new Position(bomb.getPosition());
						for (int j = 0; j < exploSize; j++) {
							dst = direction.nextPosition(dst);
						}
						if (this.game.getLevel() == level)
							animateExplosion(src, dst);
					}
					bomb.remove();
					player.addAvailableBombs(1);
				}
			}
		}
	}

	private void animateExplosion(Position src, Position dst) {
		ImageView explosion = new ImageView(ImageResource.EXPLOSION.getImage());
		TranslateTransition tt = new TranslateTransition(Duration.millis(200), explosion);
		tt.setFromX((double) src.x() * Sprite.SIZE);
		tt.setFromY((double) src.y() * Sprite.SIZE);
		tt.setToX((double) dst.x() * Sprite.SIZE);
		tt.setToY((double) dst.y() * Sprite.SIZE);
		tt.setOnFinished(e -> layer.getChildren().remove(explosion));
		layer.getChildren().add(explosion);
		tt.play();
	}

	private void createNewBombs() {
		Position playerPos = this.player.getPosition();
		int level = this.game.getLevel();
		if (this.player.getAvailableBombs() > 0) {
			if (this.game.getBombs().isThereObject(playerPos, level) == -1) {
				Bomb newBomb = new Bomb(playerPos, this.player.getBombRange());
				this.game.getBombs().add(newBomb, level);
				sprites.add(SpriteFactory.create(layer, newBomb));
				this.player.addAvailableBombs(-1);
			}
		}
	}

	private void checkCollision() {
		if (!this.player.isInvincible()) {
			Position playerPosition = this.player.getPosition();
			for (Monster m : this.game.getMonsters().getObjects(this.game.getLevel())) {
				if (m.getPosition().equals(playerPosition)) {
					this.player.addLives(-1);
					this.player.getInvincibilityTimer().start();
				}
			}
		}
	}

	private void processInput() {
		if (input.isExit()) {
			gameLoop.stop();
			Platform.exit();
			System.exit(0);
		} else if (input.isMoveDown()) {
			player.requestMove(Direction.DOWN);
		} else if (input.isMoveLeft()) {
			player.requestMove(Direction.LEFT);
		} else if (input.isMoveRight()) {
			player.requestMove(Direction.RIGHT);
		} else if (input.isMoveUp()) {
			player.requestMove(Direction.UP);
		} else if (input.isKey()) {
			player.openDoor();
		} else if (input.isBomb())
			createNewBombs();
		input.clear();
	}

	private void showMessage(String msg, Color color) {
		Text waitingForKey = new Text(msg);
		waitingForKey.setTextAlignment(TextAlignment.CENTER);
		waitingForKey.setFont(new Font(60));
		waitingForKey.setFill(color);
		StackPane root = new StackPane();
		root.getChildren().add(waitingForKey);
		Scene scene = new Scene(root, msg.length() * 50, 200, Color.WHITE);
		stage.setScene(scene);
		input = new Input(scene);
		stage.centerOnScreen();
		stage.hide();
		stage.show();
		new AnimationTimer() {
			public void handle(long now) {
				processInput();
			}
		}.start();
	}

	private void update(long now) {
		for (Monster m : this.game.getMonsters().getObjects()) {
			if (!m.getVelocityTimer().isRunning())
				m.requestMove(m.getNextDirection());
			m.update(now);
		}
		for (Bomb bomb : this.game.getBombs().getObjects()) {
			bomb.update(now);
		}
		player.update(now);
		// End Game
		if (player.getLives() <= 0) {
			gameLoop.stop();
			showMessage("YOU DIED", Color.DARKRED);
		} else if (player.tookPrincess()) {
			gameLoop.stop();
			showMessage("YOU RESCUED THE PRINCESS", Color.GOLD);
		}
		if (this.level != game.getLevel()) {
			gameLoop.stop();
			this.level = game.getLevel();
			this.changeLevel();
			this.start();
		}
	}

	public void cleanupSprites() {
		sprites.forEach(sprite -> {
			if (sprite.getGameObject().isDeleted()) {
				game.grid().remove(sprite.getPosition());
				cleanUpSprites.add(sprite);
			}
		});
		cleanUpSprites.forEach(Sprite::remove);
		sprites.removeAll(cleanUpSprites);
		cleanUpSprites.clear();
	}

	private void render() {
		sprites.forEach(Sprite::render);
	}

	public void start() {
		gameLoop.start();
	}
}