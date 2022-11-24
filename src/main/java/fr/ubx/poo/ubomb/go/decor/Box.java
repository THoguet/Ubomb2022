package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Movable;

public class Box extends Decor implements Movable {
	public Box(Position position) {
		super(position);
	}

	public Box(Game game, Position position) {
		super(game, position);
	}

	public void doMove(Direction direction) {
		Position nextPos = direction.nextPosition(getPosition());
		setPosition(nextPos);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Box && super.equals(arg0);
	}

	@Override
	public boolean canMove(Direction direction) {
		return true;
	}
}
