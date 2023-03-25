/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view.sprite;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.doors.Door;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;
import fr.ubx.poo.ubomb.go.decor.*;
import javafx.scene.layout.Pane;

import static fr.ubx.poo.ubomb.view.ImageResource.*;

public final class SpriteFactory {
	private SpriteFactory() {
	}

	public static Sprite create(Pane layer, GameObject gameObject) {
		if (gameObject instanceof Stone)
			return new Sprite(layer, STONE.getImage(), gameObject);
		if (gameObject instanceof Tree)
			return new Sprite(layer, TREE.getImage(), gameObject);
		if (gameObject instanceof Key)
			return new Sprite(layer, KEY.getImage(), gameObject);
		if (gameObject instanceof Box)
			return new Sprite(layer, BOX.getImage(), gameObject);
		if (gameObject instanceof Princess)
			return new Sprite(layer, PRINCESS.getImage(), gameObject);
		if (gameObject instanceof Monster)
			return new SpriteChara(layer, (Monster) gameObject);
		if (gameObject instanceof BombRangeInc)
			return new Sprite(layer, BONUS_BOMB_RANGE_INC.getImage(), gameObject);
		if (gameObject instanceof BombRangeDec)
			return new Sprite(layer, BONUS_BOMB_RANGE_DEC.getImage(), gameObject);
		if (gameObject instanceof BombNumberInc)
			return new Sprite(layer, BONUS_BOMB_NB_INC.getImage(), gameObject);
		if (gameObject instanceof BombNumberDec)
			return new Sprite(layer, BONUS_BOMB_NB_DEC.getImage(), gameObject);
		if (gameObject instanceof Heart)
			return new Sprite(layer, HEART.getImage(), gameObject);
		if (gameObject instanceof DoorNext dn && !dn.isOpen())
			return new SpriteDoor(layer, dn);
		if (gameObject instanceof DoorNext || gameObject instanceof DoorPrev)
			return new SpriteDoor(layer, (Door) gameObject);
		if (gameObject instanceof Bomb)
			return new SpriteBomb(layer, (Bomb) gameObject);
		throw new RuntimeException("Unsupported sprite for decor " + gameObject);
	}
}
