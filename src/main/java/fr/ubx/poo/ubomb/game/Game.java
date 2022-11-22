package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;

import java.util.LinkedList;
import java.util.List;

public class Game {

	private final Configuration configuration;
	private final Player player;
	private final Grid[] grid;
	private int level;
	private final Monsters leveledMonsters;

	public Game(Configuration configuration, Grid grid) {
		this(configuration, grid, new Monsters());
	}

	public Game(Configuration config, Grid[] grid) {
		this.configuration = config;
		this.level = 0;
		this.grid = grid;
		player = new Player(this, configuration.playerPosition());
		this.leveledMonsters = new Monsters();
	}

	public Game(Configuration config, Grid gridToAdd, Monsters monsters) {
		this.configuration = config;
		this.grid = new Grid[1];
		this.grid[0] = gridToAdd;
		this.level = 0;
		player = new Player(this, configuration.playerPosition());
		this.leveledMonsters = monsters;
	}

	public Configuration configuration() {
		return configuration;
	}

	public Monsters getMonsters() {
		return leveledMonsters;
	}

	public List<Monster> getThisLevelMonster() {
		return leveledMonsters.getMonsters(this.level);
	}

	// Returns the player, monsters and bomb at a given position
	public List<GameObject> getGameObjects(Position position) {
		List<GameObject> gos = new LinkedList<>();
		if (player().getPosition().equals(position))
			gos.add(player);
		return gos;
	}

	public Grid grid() {
		return this.grid[this.level];
	}

	public Player player() {
		return this.player;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
