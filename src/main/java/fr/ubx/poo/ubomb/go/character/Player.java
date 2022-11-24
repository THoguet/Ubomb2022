/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.*;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNextOpened;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrevOpened;

public class Player extends Character implements TakeVisitor {
	private int lives;
	private int availableBombs;
	private int bombRange;
	private int keys;
	private int nbBombsMax;
	private boolean takenPrincess = false;
	private long invisibilityStart = 0;

	public Player(Position position) {
		super(position);
		this.lives = game.configuration().playerLives();
		this.nbBombsMax = game.configuration().bombBagCapacity();
		this.availableBombs = game.configuration().bombBagCapacity();
		this.bombRange = 1;
		this.keys = 0;
	}

	public Player(Game game, Position position) {
		super(game, position);
		this.lives = game.configuration().playerLives();
	}

	public long getInvisibilityStart() {
		return invisibilityStart;
	}

	public void setInvisibilityStart(long invisibilityStart) {
		this.invisibilityStart = invisibilityStart;
	}

	public boolean isInvisibile(long now) {
		return (this.invisibilityStart + this.game.configuration().playerInvisibilityTime() * 1000000) - now > 0;
	}

	@Override
	public void take(Key key) {
		System.out.println("Take the key ...");
		this.addKeys(1);
	}

	@Override
	public void take(Princess p) {
		this.takenPrincess = true;
	}

	public void take(Bonus bonus) {
		// TODO -> bonus
		// switch (bonus) {

		// }
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
		if (next instanceof Takeable taken) {
			taken.takenBy(this);
		}
		setPosition(nextPos);
	}

	@Override
	public final boolean canMove(Direction direction) {
		Position nextPos = direction.nextPosition(getPosition());
		Decor tmp = game.grid().get(nextPos);
		boolean inside = game.grid().inside(nextPos);
		boolean walkable = true;
		if (tmp != null){
			walkable = tmp.walkableBy(game.player());
			if (tmp instanceof Box){
				if (((Box) tmp).canMove(direction)){
					((Box) tmp).doMove(direction);
					return true;
				}
				return false;
			}
		}
		return inside && walkable;
	}

	public boolean tookPrincess() {
		return this.takenPrincess;
	}

	@Override
	public void take(DoorNextOpened door) {
		this.game.nextLevel();
	}

	@Override
	public void take(DoorPrevOpened door) {
		this.game.prevLevel();
	}
}
