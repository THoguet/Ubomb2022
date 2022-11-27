/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.game;

import java.util.Random;

public enum Direction {
	UP {
		@Override
		public Position nextPosition(Position pos, int delta) {
			return new Position(pos.x(), pos.y() - delta);
		}

		@Override
		public Direction opposit() {
			return Direction.DOWN;
		}
	},
	RIGHT {
		@Override
		public Position nextPosition(Position pos, int delta) {
			return new Position(pos.x() + delta, pos.y());
		}

		@Override
		public Direction opposit() {
			return Direction.LEFT;
		}
	},
	DOWN {
		@Override
		public Position nextPosition(Position pos, int delta) {
			return new Position(pos.x(), pos.y() + delta);
		}

		@Override
		public Direction opposit() {
			return Direction.UP;
		}
	},
	LEFT {
		@Override
		public Position nextPosition(Position pos, int delta) {
			return new Position(pos.x() - delta, pos.y());
		}

		@Override
		public Direction opposit() {
			return Direction.RIGHT;
		}
	},
	;

	private static final Random randomGenerator = new Random();

	public static Direction random() {
		int i = randomGenerator.nextInt(values().length);
		return values()[i];
	}

	public abstract Position nextPosition(Position pos, int delta);

	public abstract Direction opposit();

	public Position nextPosition(Position pos) {
		return nextPosition(pos, 1);
	}

}