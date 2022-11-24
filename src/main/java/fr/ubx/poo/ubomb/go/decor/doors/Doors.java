package fr.ubx.poo.ubomb.go.decor.doors;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Decor;

public abstract class Doors extends Decor implements Takeable {
	public Doors(Position position) {
		super(position);
	}

	@Override
	public boolean walkableBy(Player player) {
		return true;
	}

	@Override
	public boolean equals(Object arg0) {
		return (arg0 instanceof Doors) && super.equals(arg0);
	}
}
