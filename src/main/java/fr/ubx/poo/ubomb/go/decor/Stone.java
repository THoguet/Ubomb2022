/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Position;

public class Stone extends Decor {
	public Stone(Position position) {
		super(position);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Stone && super.equals(arg0);
	}
}
