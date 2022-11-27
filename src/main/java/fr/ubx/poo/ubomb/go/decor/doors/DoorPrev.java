package fr.ubx.poo.ubomb.go.decor.doors;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class DoorPrev extends Door {

	public DoorPrev(Position position) {
		super(position, true);
	}

	@Override
	public void takenBy(Player player) {
		player.take(this);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof DoorPrev && super.equals(arg0);
	}
}
