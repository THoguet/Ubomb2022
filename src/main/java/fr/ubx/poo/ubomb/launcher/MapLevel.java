package fr.ubx.poo.ubomb.launcher;

public class MapLevel {

	private final int height;
	private final int width;
	private final Entity[][] grid;

	public MapLevel(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new Entity[width][height];
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	public Entity get(int i, int j) {
		return grid[i][j];
	}

	public void set(int i, int j, Entity entity) {
		grid[i][j] = entity;
	}

}
