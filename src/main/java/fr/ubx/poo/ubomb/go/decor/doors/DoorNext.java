package fr.ubx.poo.ubomb.go.decor.doors;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class DoorNext extends Door {

	public DoorNext(Position p, boolean open) {
		super(p, open);
	}

	@Override
	public boolean walkableBy(Player player) {
		return this.isOpen();
	}

	@Override
	public void takenBy(Player player) {
		player.take(this);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof DoorNext && super.equals(arg0);
	}

}
