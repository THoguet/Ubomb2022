package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.Takeable;

public class Box extends Decor implements Movable {
	public Box(Position position) {
		super(position);
	}

	public boolean canMove(Direction direction){
		Position nextPos = direction.nextPosition(getPosition());
		Decor tmp = game.grid().get(nextPos);
		boolean inside = game.grid().inside(nextPos);
		boolean walkable = true;
		if (tmp != null)
			walkable = tmp.walkableBy(game.player());
		return inside && walkable;
	}

	public void doMove(Direction direction){
		Position nextPos = direction.nextPosition(getPosition());
		setPosition(nextPos);
	}
}
