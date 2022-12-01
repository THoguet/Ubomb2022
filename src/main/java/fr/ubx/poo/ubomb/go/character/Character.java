package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.WalkVisitor;

public abstract class Character extends GameObject implements Movable, WalkVisitor {
	private Direction direction;
	private boolean moveRequested = false;
	private final Timer invincibilityTimer;
	private int lives;

	public Character(Game game, Position position, int lives, Timer timer) {
		super(game, position);
		this.direction = Direction.DOWN;
		this.invincibilityTimer = timer;
		this.lives = lives;
	}

	public void requestMove(Direction direction) {
		if (direction != this.direction) {
			this.direction = direction;
			setModified(true);
		}
		moveRequested = true;
	}

	public Timer getInvincibilityTimer() {
		return invincibilityTimer;
	}

	// Lives
	public int getLives() {
		return lives;
	}

	public void addLives(int delta) {
		lives += delta;
	}

	@Override
	public void doMove(Direction direction) {
		// This method is called only if the move is possible, do not check again
		Position nextPos = direction.nextPosition(getPosition());
		setPosition(nextPos);
	}

	public Direction getDirection() {
		return direction;
	}

	public void update(long now) {
		this.invincibilityTimer.update(now);
		if (moveRequested && canMove(direction)) {
			doMove(direction);
		}
		moveRequested = false;
	}

	@Override
	public void explode() {
		// TODO
	}
}
