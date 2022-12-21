package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Chara;
import fr.ubx.poo.ubomb.go.Movable;

public class Box extends Decor implements Movable {
	public Box(Position position) {
		super(position);
	}

	public Box(Game game, Position position) {
		super(game, position);
	}

	@Override
	public void doMove(Direction direction) {
		Position nextPos = direction.nextPosition(getPosition());
		setPosition(nextPos);
	}

	@Override
	public boolean canMove(Direction direction) {
		Position nextPos = direction.nextPosition(getPosition());
		int indexMonster = this.game.getMonsters().isThereObject(nextPos, this.game.getLevel());
		int indexBox = this.game.getBoxes().isThereObject(nextPos, this.game.getLevel());
		Decor tmp = game.grid().get(nextPos);
		boolean inside = game.grid().inside(nextPos);
		return inside && indexMonster == -1 && indexBox == -1 && (tmp == null || tmp.walkableBy(this));
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Box && super.equals(arg0);
	}

	@Override
	public boolean walkableBy(Chara c) {
		return c.walk(this);
	}

}
