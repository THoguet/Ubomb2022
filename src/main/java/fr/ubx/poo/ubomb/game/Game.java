package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;
import fr.ubx.poo.ubomb.launcher.MapException;

import java.util.LinkedList;
import java.util.List;

public class Game {

	private final Configuration configuration;
	private final Player player;
	private final Grid[] grid;
	private int level;
	private final NonStaticObject<Monster> monsters;
	private final NonStaticObject<Box> boxes;

	public Game(Configuration configuration, Grid grid) {
		this(configuration, grid, new NonStaticObject<Monster>(), new NonStaticObject<>());
	}

	public Game(Configuration config, Grid[] grid) {
		this.configuration = config;
		this.level = 0;
		this.grid = grid;
		player = new Player(this, configuration.playerPosition());
		this.monsters = new NonStaticObject<Monster>();
		this.boxes = new NonStaticObject<Box>();
	}

	public Game(Configuration config, Grid gridToAdd, NonStaticObject<Monster> monsters, NonStaticObject<Box> boxes) {
		this.configuration = config;
		this.grid = new Grid[1];
		this.grid[0] = gridToAdd;
		this.level = 0;
		player = new Player(this, configuration.playerPosition());
		this.monsters = monsters;
		this.boxes = boxes;
	}

	public Configuration configuration() {
		return configuration;
	}

	public NonStaticObject<Monster> getMonsters() {
		return monsters;
	}

	public List<Monster> getThisLevelMonsters() {
		return monsters.getObjects(this.level);
	}

	public NonStaticObject<Box> getBoxes() {
		return this.boxes;
	}

	public List<Box> getThisLevelBoxes() {
		return this.boxes.getObjects(this.level);
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

	public void addLevel(int delta) {
		this.level += delta;
		this.player.setPosition(new Position(this.getLevelDoor(delta > 0)));
	}

	public Position getLevelDoor(boolean next) throws MapException {
		if (this.level >= this.grid.length)
			throw new MapException("The level " + this.level + " doesn't exist for this map!");
		for (int i = 0; i < this.grid[this.level].width(); i++) {
			for (int j = 0; j < this.grid[this.level].height(); j++) {
				Position p = new Position(i, j);
				Decor d = this.grid[this.level].get(p);
				if (d != null) {
					if (next) {
						if (d instanceof DoorPrev)
							return p;
					} else {
						if (d instanceof DoorNext)
							return p;
					}
				}
			}
		}
		throw new MapException("No opened door in next level");
	}
}
