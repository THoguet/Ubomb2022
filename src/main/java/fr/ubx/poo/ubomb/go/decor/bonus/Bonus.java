/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.decor.Decor;

public abstract class Bonus extends Decor implements Takeable {
	public Bonus(Position position) {
		super(position);
	}

	@Override
	public void explode() {
		remove();
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Bonus && super.equals(arg0);
	}
}
