package fr.ubx.poo.ubomb.go.decor.doors;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.character.Chara;

public class DoorPrev extends Door {

	public DoorPrev(Position position) {
		super(position, true);
	}

	@Override
	public boolean walkableBy(Chara c) {
		return c.walk(this);
	}

	@Override
	public void takenBy(Chara c) {
		c.take(this);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof DoorPrev && super.equals(arg0);
	}
}
