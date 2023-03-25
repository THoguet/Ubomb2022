package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Chara;

public class Princess extends Bonus {

	public Princess(Position position) {
		super(position);
	}

	@Override
	public void explode() {
	}

	@Override
	public void takenBy(Chara c) {
		c.take(this);
	}

	@Override
	public boolean walkableBy(Chara c) {
		return c.walk(this);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Princess && super.equals(arg0);
	}
}
