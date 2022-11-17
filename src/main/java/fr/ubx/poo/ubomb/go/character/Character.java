package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;

public abstract class Character extends GameObject implements Movable {
	private Direction direction;
	private boolean moveRequested = false;

	public Character(Position position) {
		super(position);
		this.direction = Direction.DOWN;
	}

	public Character(Game game, Position position) {
		super(game, position);
		this.direction = Direction.DOWN;
	}

	public void requestMove(Direction direction) {
		if (direction != this.direction) {
			this.direction = direction;
			setModified(true);
		}
		moveRequested = true;
	}

	@Override
	public void doMove(Direction direction) {
		// This method is called only if the move is possible, do not check again
		Position nextPos = direction.nextPosition(getPosition());
		setPosition(nextPos);
	}

	public Direction getDirection() {
		return direction;
	}

	public void update(long now) {
		if (moveRequested && canMove(direction)) {
			doMove(direction);
		}
		moveRequested = false;
	}

	@Override
	public void explode() {
		// TODO
	}
}
