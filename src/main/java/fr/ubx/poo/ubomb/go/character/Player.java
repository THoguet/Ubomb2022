/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;

public class Player extends Chara {
	private int nbBombsMax;
	private int availableBombs;
	private int bombRange;
	private int keys;

	private boolean takenPrincess = false;

	public Player(Game game, Position position) {
		super(game, position, game.configuration().playerLives(),
				new Timer(game.configuration().playerInvisibilityTime()));
		this.nbBombsMax = game.configuration().bombBagCapacity();
		this.availableBombs = game.configuration().bombBagCapacity();
		this.bombRange = 1;
		this.keys = 0;
	}

	@Override
	public void take(Key key) {
		this.addKeys(1);
		key.remove();
	}

	@Override
	public void take(Princess p) {
		this.takenPrincess = true;
	}

	public void take(BombNumberInc BNI) {
		this.addAvailableBombs(1);
		this.addNbBombsMax(1);
		BNI.remove();
	}

	public void take(BombNumberDec BND) {
		if (getNbBombsMax() > 1)
			this.addNbBombsMax(-1);
		BND.remove();
	}

	public void take(BombRangeInc BRI) {
		this.addBombRange(1);
		BRI.remove();
	}

	public void take(BombRangeDec BRD) {
		if (getBombRange() > 1)
			this.addBombRange(-1);
		BRD.remove();
	}

	public void take(Heart heart) {
		this.addLives(1);
		heart.remove();
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
		Position nextPos = direction.nextPosition(getPosition());
		int indexBox = this.game.getBoxes().isThereObject(nextPos, this.game.getLevel());
		if (indexBox != -1)
			this.game.getBoxes().getObjects(this.game.getLevel()).get(indexBox).doMove(direction);
		super.doMove(direction);
	}

	@Override
	public boolean canMove(Direction direction) {
		Position nextPos = direction.nextPosition(this.getPosition());
		if (!game.grid().inside(nextPos))
			return false;
		int indexBox = this.game.getBoxes().isThereObject(nextPos, this.game.getLevel());
		if (indexBox != -1)
			return this.game.getBoxes().getObjects(this.game.getLevel()).get(indexBox).canMove(direction);
		Decor d = game.grid().get(nextPos);
		if (d != null)
			return d.walkableBy((Chara) this);
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

	@Override
	public boolean walk(DoorPrev d) {
		return true;
	}

	@Override
	public boolean walk(DoorNext d) {
		return d.isOpen();
	}

	@Override
	public boolean walk(BombNumberDec b) {
		return true;
	}

	@Override
	public boolean walk(BombNumberInc b) {
		return true;
	}

	@Override
	public boolean walk(BombRangeDec b) {
		return true;
	}

	@Override
	public boolean walk(BombRangeInc b) {
		return true;
	}

	@Override
	public boolean walk(Heart b) {
		return true;
	}

	@Override
	public boolean walk(Key b) {
		return true;
	}

	@Override
	public boolean walk(Princess b) {
		return true;
	}
}
