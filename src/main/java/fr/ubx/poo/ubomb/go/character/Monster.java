package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;

//TODO
//fusion Monster + Player (movable)
//princess reste seule car =/= movable
public class Monster extends Character {

	public Monster(Position position) {
		super(position);
	}

	public Monster(Game game, Position position) {
		super(game, position);
	}

	public boolean canMove(Direction direction) {
		return true;
	}

	@Override
	public void doMove(Direction direction) {
		// TODO
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Monster && super.equals(arg0);
	}

}
