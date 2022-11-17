package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.character.Monster;

public class Monsters {
	private final Game game;
	private final Monster[][] monstersByLevel;

	public Monsters(Game g, Monster[][] m) {
		this.monstersByLevel = m;
		this.game = g;
	}

	public boolean isThereMonster(Position pos) {
		for (int i = 0; i < this.monstersByLevel[this.game.getLevel()].length; i++) {
			if (this.monstersByLevel[this.game.getLevel() - 1][i].getPosition().equals(pos))
				return true;
		}
		return false;
	}

	public Monster[] getMonsters() {
		return this.monstersByLevel[this.game.getLevel() - 1];
	}

	public Monster[] getMonsters(int level) {
		return this.monstersByLevel[level];
	}
}
