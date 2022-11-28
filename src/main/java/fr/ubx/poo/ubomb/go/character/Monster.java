package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.decor.Decor;

//TODO
//fusion Monster + Player (movable)
//princess reste seule car =/= movable
public class Monster extends Character {
	private final Timer velocityTimer;
	private final int level;

	public Monster(Game game, Position position, int level) {
		super(game, position, new Timer(game.configuration().monsterInvisibilityTime()));
		this.level = level;
		this.velocityTimer = new Timer(game.configuration().monsterVelocity() * 1000);
	}

	@Override
	public boolean canMove(Direction direction) {
		Position nextPos = direction.nextPosition(this.getPosition());
		if (!game.grid(this.level).inside(nextPos))
			return false;
		int indexBox = this.game.getBoxes().isThereObject(nextPos, this.level);
		if (indexBox != -1)
			return this.game.getBoxes().getObjects(this.level).get(indexBox).walkableBy(this);
		int indexMonster = this.game.getMonsters().isThereObject(nextPos, this.level);
		if (indexMonster != -1)
			return this.game.getMonsters().getObjects(this.level).get(indexMonster).walkableBy(this);
		Decor tmp = game.grid(this.level).get(nextPos);
		if (tmp != null)
			return tmp.walkableBy(this);
		return true;
	}

	@Override
	public void requestMove(Direction direction) {
		super.requestMove(direction);
		this.velocityTimer.start();
	}

	@Override
	public void doMove(Direction direction) {
		// This method is called only if the move is possible, do not check again
		Position nextPos = direction.nextPosition(getPosition());
		GameObject next = game.grid().get(nextPos);
		setPosition(nextPos);
		if (next instanceof Takeable taken)
			taken.takenBy(this);
	}

	public Timer getVelocityTimer() {
		return velocityTimer;
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Monster && super.equals(arg0);
	}

	@Override
	public void update(long now) {
		this.velocityTimer.update(now);
		super.update(now);
	}
}
