package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.character.Player;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Game {

	private final Configuration configuration;
	private final Player player;
	private final Grid[] grid;
	private int level;

	public Game(Configuration configuration, Grid grid) {
		this.level = 1;
		this.configuration = configuration;
		this.grid = new Grid[1];
		this.grid[0] = grid;
		player = new Player(this, configuration.playerPosition());
	}

	public Configuration configuration() {
		return configuration;
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
