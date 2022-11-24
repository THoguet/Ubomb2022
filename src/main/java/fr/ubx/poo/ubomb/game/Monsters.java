package fr.ubx.poo.ubomb.game;

import java.util.ArrayList;
import java.util.List;

import fr.ubx.poo.ubomb.go.character.Monster;

public class Monsters {
	private final List<List<Monster>> monstersByLevel;

	public Monsters() {
		monstersByLevel = new ArrayList<>();
	}

	public boolean isThereMonster(Position pos, int level) {
		for (int i = 0; i < this.monstersByLevel.get(level).size(); i++) {
			if (this.monstersByLevel.get(level).get(i).getPosition().equals(pos))
				return true;
		}
		return false;
	}

	public void add(Monster m, int level) {
		while (this.monstersByLevel.size() <= level) {
			this.monstersByLevel.add(new ArrayList<>());
		}
		this.monstersByLevel.get(level).add(m);
	}

	public List<Monster> getMonstersByLevel(int level) {
		if (level >= this.monstersByLevel.size())
			return new ArrayList<>();
		return this.monstersByLevel.get(level);
	}

}
