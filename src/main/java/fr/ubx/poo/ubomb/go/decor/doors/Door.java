package fr.ubx.poo.ubomb.go.decor.doors;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Decor;

public abstract class Door extends Decor implements Takeable {

	private boolean open;

	public Door(Position p, boolean open) {
		super(p);
		this.open = open;
	}

	@Override
	public boolean walkableBy(Player player) {
		return true;
	}

	@Override
	public boolean equals(Object arg0) {
		return (arg0 instanceof Door d) && d.isOpen() == this.isOpen() && super.equals(arg0);
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
