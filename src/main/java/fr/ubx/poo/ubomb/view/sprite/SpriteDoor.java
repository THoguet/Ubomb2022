package fr.ubx.poo.ubomb.view.sprite;

import fr.ubx.poo.ubomb.go.decor.doors.Door;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import static fr.ubx.poo.ubomb.view.ImageResource.*;

public class SpriteDoor extends Sprite {

	public SpriteDoor(Pane layer, Door door) {
		super(layer, null, door);
		updateImage();
	}

	@Override
	public void updateImage() {
		Door door = (Door) this.getGameObject();
		Image image = door.isOpen() ? DOOR_OPENED.getImage() : DOOR_CLOSED.getImage();
		setImage(image);
	}
}