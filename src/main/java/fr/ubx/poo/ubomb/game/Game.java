package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.character.Chara;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Bomb;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;
import fr.ubx.poo.ubomb.graph.Graph;
import fr.ubx.poo.ubomb.launcher.MapException;

public class Game {

	private final Configuration configuration;
	private final Player player;
	private final Grid[] grid;
	private final NonStaticObject<Monster> monsters;
	private final NonStaticObject<Box> boxes;
	private final NonStaticObject<Bomb> bombs;

	private int level;
	private final int levels;

	public Game(Configuration configuration, Grid grid) {
		this(configuration, grid, new NonStaticObject<>(), new NonStaticObject<>(), new NonStaticObject<>());
	}

	public Game(Configuration config, Grid[] grid) {
		this.configuration = config;
		this.level = 0;
		this.levels = grid.length - 1;
		this.grid = grid;
		player = new Player(this, configuration.playerPosition());
		this.monsters = new NonStaticObject<>();
		this.boxes = new NonStaticObject<>();
		this.bombs = new NonStaticObject<>();
	}

	public Game(Configuration config, Grid gridToAdd, NonStaticObject<Monster> monsters, NonStaticObject<Box> boxes,
			NonStaticObject<Bomb> bombs) {
		this.configuration = config;
		this.grid = new Grid[1];
		this.grid[0] = gridToAdd;
		this.level = 0;
		this.levels = 0;
		player = new Player(this, configuration.playerPosition());
		this.monsters = monsters;
		this.boxes = boxes;
		this.bombs = bombs;
	}

	public Configuration configuration() {
		return configuration;
	}

	public NonStaticObject<Monster> getMonsters() {
		return monsters;
	}

	public NonStaticObject<Bomb> getBombs() {
		return bombs;
	}

	public NonStaticObject<Box> getBoxes() {
		return this.boxes;
	}

	public Grid grid() {
		return this.grid[this.level];
	}

	public Grid grid(int level) {
		return this.grid[level];
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
		this.player.setMovedFrom(null);
	}

	public int getLevels() {
		return levels;
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

	public boolean isEmpty(Position p, Chara c) {
		if (p.y() < 0 || p.y() >= this.grid().height() || p.x() < 0 || p.x() >= this.grid().width())
			return false;
		Decor d = this.grid().get(p);
		int indexBox = this.getBoxes().isThereObject(p, this.level);
		return ((d == null || d.walkableBy(c))
				&& (indexBox == -1 || this.getBoxes().getObjects(this.level).get(indexBox).walkableBy(c)));
	}

	public Graph<Position> getGraph(Chara walkableCharacter) {
		Graph<Position> g = new Graph<>();
		for (int i = 0; i < this.grid().width(); i++) {
			for (int j = 0; j < this.grid().height(); j++) {
				if (isEmpty(new Position(i, j), walkableCharacter))
					g.addNode(new Position(i, j));
			}
		}
		for (int i = 0; i < this.grid().width(); i++) {
			for (int j = 0; j < this.grid().height(); j++) {
				if (isEmpty(new Position(i, j), walkableCharacter)) {
					for (Direction direction : Direction.values()) {
						Position nextDir = direction.nextPosition(new Position(i, j));
						if (isEmpty(nextDir, walkableCharacter)) {
							g.getNode(new Position(i, j)).addEdge(g.getNode(new Position(nextDir)));
						}
					}
				}
			}
		}
		return g;
	}
}