package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.character.Monster;

public class Monsters {
	private final Game game;
	private final Monster[][] monsters;

	public Monsters(Game g, Monster[][] m) {
		this.monsters = m;
		this.game = g;
	}

	public boolean isMonster(Position pos) {
		for (int i = 0; i < this.monsters[this.game.getLevel()].length; i++) {
			if (this.monsters[this.game.getLevel()][i].getPosition().equals(pos))
				return true;
		}
		return false;
	}
}
