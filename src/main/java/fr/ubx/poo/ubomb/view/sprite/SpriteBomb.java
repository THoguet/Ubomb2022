package fr.ubx.poo.ubomb.view.sprite;

import fr.ubx.poo.ubomb.go.decor.Bomb;
import fr.ubx.poo.ubomb.view.ImageResourceFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpriteBomb extends Sprite {

	public SpriteBomb(Pane layer, Bomb bomb) {
		super(layer, null, bomb);
		updateImage();
	}

	@Override
	public void updateImage() {
		Bomb bomb = (Bomb) this.getGameObject();
		Image image = ImageResourceFactory.getBomb(bomb.getImageId()).getImage();
		setImage(image);
	}
}