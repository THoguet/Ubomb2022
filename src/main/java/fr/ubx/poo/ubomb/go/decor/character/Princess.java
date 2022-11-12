package fr.ubx.poo.ubomb.go.decor.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.TakeVisitor;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.decor.bonus.*;

public class Princess extends Character implements Takeable {

	public Princess(Position position) {
		super(position);
	}

	public Princess(Game game, Position position) {
		super(game, position);
	}

	@Override
	public boolean canMove(Direction direction) {
		return false;
	}

	@Override
	public boolean walkableBy(Player player) {
		return true;
	}

	@Override
	public void explode() {
		remove();
	}

	@Override
	public void takenBy(Player player) {
		player.take(this);
	}
}
