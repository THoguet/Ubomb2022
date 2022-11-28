/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.*;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;

public class Player extends Character implements TakeVisitor {
	private int lives;
	private int nbBombsMax;
	private int availableBombs;
	private int bombRange;
	private int keys;

	private boolean takenPrincess = false;

	public Player(Game game, Position position) {
		super(game, position, new Timer(game.configuration().playerInvisibilityTime()));
		this.lives = game.configuration().playerLives();
		this.nbBombsMax = game.configuration().bombBagCapacity();
		this.availableBombs = game.configuration().bombBagCapacity();
		this.bombRange = 1;
		this.keys = 0;
		this.lives = game.configuration().playerLives();
	}

	public boolean isInvisible(long now) {
		return this.getInvincibilityTimer().isRunning();
	}

	@Override
	public void take(Key key) {
		System.out.println("Take the key ...");
		this.addKeys(1);
		key.remove();
	}

	@Override
	public void take(Princess p) {
		this.takenPrincess = true;
	}

	public void take(BombNumberInc BNI) {
		System.out.println("bomb number +");
		this.addAvailableBombs(1);
		this.addNbBombsMax(1);
		BNI.remove();
	}

	public void take(BombNumberDec BND) {
		System.out.println("bomb number -");
		if (getNbBombsMax() > 1)
			this.addNbBombsMax(-1);
		BND.remove();
	}

	public void take(BombRangeInc BRI) {
		System.out.println("bomb range +");
		this.addBombRange(1);
		BRI.remove();
	}

	public void take(BombRangeDec BRD) {
		System.out.println("bomb number -");
		if (getBombRange() > 1)
			this.addBombRange(-1);
		BRD.remove();
	}

	public void take(Heart heart) {
		System.out.println("healing");
		this.addLives(1);
		heart.remove();
	}

	// Lives
	public int getLives() {
		return lives;
	}

	public void addLives(int delta) {
		lives += delta;
	}

	// nbBombsMax
	public int getNbBombsMax() {
		return nbBombsMax;
	}

	public void addNbBombsMax(int delta) {
		nbBombsMax += delta;
	}

	// AvailableBombs
	public int getAvailableBombs() {
		return availableBombs;
	}

	public void addAvailableBombs(int delta) {
		availableBombs += delta;
	}

	// BombRange
	public int getBombRange() {
		return bombRange;
	}

	public void addBombRange(int delta) {
		bombRange += delta;
	}

	// Keys
	public int getKeys() {
		return keys;
	}

	public void addKeys(int delta) {
		keys += delta;
	}

	@Override
	public void doMove(Direction direction) {
		// This method is called only if the move is possible, do not check again
		Position nextPos = direction.nextPosition(getPosition());
		GameObject next = game.grid().get(nextPos);
		int indexBox = this.game.getBoxes().isThereObject(nextPos, this.game.getLevel());
		if (indexBox != -1)
			this.game.getBoxes().getObjects(this.game.getLevel()).get(indexBox).doMove(direction);
		setPosition(nextPos);
		if (next instanceof Takeable taken)
			taken.takenBy(this);
	}

	@Override
	public boolean canMove(Direction direction) {
		Position nextPos = direction.nextPosition(this.getPosition());
		if (!game.grid().inside(nextPos))
			return false;
		int indexBox = this.game.getBoxes().isThereObject(nextPos, this.game.getLevel());
		if (indexBox != -1)
			return this.game.getBoxes().getObjects(this.game.getLevel()).get(indexBox).canMove(direction);
		Decor tmp = game.grid().get(nextPos);
		if (tmp != null)
			return tmp.walkableBy(this);
		return true;
	}

	public boolean tookPrincess() {
		return this.takenPrincess;
	}

	@Override
	public void take(DoorNext door) {
		if (door.isOpen())
			this.game.addLevel(1);
	}

	@Override
	public void take(DoorPrev door) {
		this.game.addLevel(-1);
	}

	public boolean canBoxMove(Box box, Direction direction) {
		Position nextPos = direction.nextPosition(box.getPosition());
		if (!game.grid().inside(nextPos))
			return false;
		Decor tmp = game.grid().get(nextPos);
		return tmp != null && tmp.walkableBy(box);
	}

	public void openDoor() {
		if (this.keys <= 0)
			return;
		Position nextPos = this.getDirection().nextPosition(this.getPosition());
		if (this.game.grid().get(nextPos) instanceof DoorNext dn && !dn.isOpen()) {
			dn.setOpen(true);
			dn.setModified(true);
			this.keys--;
		}
	}
}
