/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Chara;

public class Tree extends Decor {
	public Tree(Position position) {
		super(position);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Tree && super.equals(arg0);
	}

	@Override
	public boolean walkableBy(Chara c) {
		return c.walk(this);
	}

}
