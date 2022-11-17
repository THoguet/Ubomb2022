/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.*;
import fr.ubx.poo.ubomb.go.decor.bonus.*;

public class Player extends Character implements TakeVisitor {
	private int lives;
	private boolean takenPrincess = false;

	public Player(Position position) {
		super(position);
		this.lives = game.configuration().playerLives();
	}

	public Player(Game game, Position position) {
		super(game, position);
		this.lives = game.configuration().playerLives();
	}

	@Override
	public void take(Key key) {
		System.out.println("Take the key ...");
	}

	@Override
	public void take(Princess p) {
		this.takenPrincess = true;
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

	public int getLives() {
		return lives;
	}

	@Override
	public final boolean canMove(Direction direction) {
		boolean canMove;
		switch (direction) {
			case UP -> {
				canMove = game.player().getPosition().y() != 0;
			}
			case DOWN -> {
				canMove = game.player().getPosition().y() != game.grid().height() - 1;
			}
			case LEFT -> {
				canMove = game.player().getPosition().x() != 0;
			}
			case RIGHT -> {
				canMove = game.player().getPosition().x() != game.grid().width() - 1;
			}
			default ->
				throw new RuntimeException("direction in the 3rd dimension");
		}
		return canMove
				&& game.grid().get(direction.nextPosition(game.player().getPosition())).walkableBy(game.player());
	}

	public boolean tookPrincess() {
		return this.takenPrincess;
	}
}
