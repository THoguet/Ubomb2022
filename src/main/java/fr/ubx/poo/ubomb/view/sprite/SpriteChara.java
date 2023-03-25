/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view.sprite;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.character.Chara;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.view.ImageResourceFactory;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SpriteChara extends Sprite {

	public SpriteChara(Pane layer, Chara c) {
		super(layer, null, c);
		updateImage();
	}

	@Override
	public void updateImage() {
		Chara c = (Chara) getGameObject();
		Image image = null;
		if (c instanceof Player)
			image = ImageResourceFactory.getPlayer(c.getDirection()).getImage();
		if (c instanceof Monster)
			image = ImageResourceFactory.getMonster(c.getDirection()).getImage();
		if (c.isInvincible() && c.isBlinking()) {
			this.setOpacity(0.3);
		} else {
			this.setOpacity(1.0);
		}
		setImage(image);
	}

	@Override
	public TranslateTransition animate(GameObject gameObj, ImageView img) {
		Chara c = (Chara) gameObj;
		Position src = c.getMovedFrom();
		if (src != null) {
			img.setX(src.x() * SpriteChara.SIZE);
			img.setY(src.y() * SpriteChara.SIZE);
			TranslateTransition tt = new TranslateTransition(Duration.millis(200), img);
			switch (c.getDirection()) {
				case UP -> tt.setByY(-SpriteChara.SIZE);
				case DOWN -> tt.setByY(SpriteChara.SIZE);
				case RIGHT -> tt.setByX(SpriteChara.SIZE);
				case LEFT -> tt.setByX(-SpriteChara.SIZE);
			}
			tt.play();
			c.setMovedFrom(null);
			return tt;
		}
		return null;
	}
}
