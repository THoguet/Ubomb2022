package fr.ubx.poo.ubomb.game;

public record Position(int x, int y) {

	public Position(Position position) {
		this(position.x, position.y);
	}

	public double distance(Position cible) {
		return Math.sqrt((this.x - cible.x) * (this.x - cible.x) + (this.y - cible.y) * (this.y - cible.y));
	}
}
