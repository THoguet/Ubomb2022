/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view.sprite;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Sprite {

	public static final int SIZE = 40;
	private final Pane layer;
	private final GameObject gameObject;
	private ImageView imageView;
	private Image image;
	private double opacity = 1.0;

	public Sprite(Pane layer, Image image, GameObject gameObject) {
		this.layer = layer;
		this.image = image;
		this.gameObject = gameObject;
	}

	public GameObject getGameObject() {
		return gameObject;
	}

	public final void setImage(Image image) {
		if (this.image == null || this.image != image) {
			this.image = image;
		}
	}

	public void updateImage() {
		// only update on movable GO
	}

	public Position getPosition() {
		return getGameObject().getPosition();
	}

	public TranslateTransition animate(GameObject gameObj, ImageView img) {
		return null;
	}

	public final void render() {
		if (gameObject.isModified()) {
			if (imageView != null) {
				remove();
			}
			updateImage();
			imageView = new ImageView(this.image);
			imageView.setOpacity(this.opacity);
			TranslateTransition tt = this.animate(gameObject, imageView);
			if (tt == null) {
				imageView.setX((double) getPosition().x() * SIZE);
				imageView.setY((double) getPosition().y() * SIZE);
			}
			layer.getChildren().add(imageView);
			gameObject.setModified(false);
		}

	}

	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	public final void remove() {
		layer.getChildren().remove(imageView);
		imageView = null;
	}
}
