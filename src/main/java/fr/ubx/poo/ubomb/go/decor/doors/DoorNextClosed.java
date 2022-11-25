package fr.ubx.poo.ubomb.go.decor.doors;

import fr.ubx.poo.ubomb.game.Position;

public class DoorNextClosed extends Doors {

	public DoorNextClosed(Position position) {
		super(position);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof DoorNextClosed && super.equals(arg0);
	}
}
