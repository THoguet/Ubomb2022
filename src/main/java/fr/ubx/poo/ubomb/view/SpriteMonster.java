package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.decor.character.Monster;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpriteMonster extends Sprite {

	public SpriteMonster(Pane layer, Monster player) {
		super(layer, null, player);
		updateImage();
	}

	@Override
	public void updateImage() {
		Monster monster = (Monster) getGameObject();
		Image image = ImageResourceFactory.getMonster(monster.getDirection()).getImage();
		setImage(image);
	}
}