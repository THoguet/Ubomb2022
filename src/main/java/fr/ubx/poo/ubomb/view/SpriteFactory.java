/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNextClosed;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNextOpened;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrevOpened;
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
			return new SpriteMonster(layer, (Monster) gameObject);
		if (gameObject instanceof DoorNextClosed)
			return new Sprite(layer, DOOR_CLOSED.getImage(), gameObject);
		if (gameObject instanceof DoorNextOpened || gameObject instanceof DoorPrevOpened)
			return new Sprite(layer, DOOR_OPENED.getImage(), gameObject);
		throw new RuntimeException("Unsupported sprite for decor " + gameObject);
	}
}
