package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;

public abstract class Decor extends GameObject {

	public Decor(Game game, Position position) {
		super(game, position);
	}

	public Decor(Position position) {
		super(position);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Decor && super.equals(arg0);
	}

}