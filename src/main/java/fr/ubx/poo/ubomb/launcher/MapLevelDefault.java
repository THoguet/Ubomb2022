package fr.ubx.poo.ubomb.launcher;

import static fr.ubx.poo.ubomb.launcher.Entity.*;

public class MapLevelDefault extends MapLevel {
	private static final Entity[][] level1 = {
			{ Empty, Empty, Empty, Princess, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty },
			{ Empty, Empty, Stone, Empty, Stone, Empty, Stone, Stone, Stone, Stone, Empty, Empty },
			{ Empty, Empty, Empty, Empty, Stone, Empty, Stone, Empty, Empty, Stone, Empty, Empty },
			{ Empty, Empty, Empty, Empty, Stone, Empty, Stone, Empty, Empty, Stone, Empty, Empty },
			{ Empty, Empty, Empty, Empty, Stone, Stone, Stone, Empty, Empty, Empty, Empty, Empty },
			{ Empty, Empty, Empty, Empty, Empty, Empty, Empty, Key, Empty, Stone, Empty, Empty },
			{ Empty, Tree, Empty, Tree, Empty, Empty, Empty, Empty, Empty, Stone, Empty, Empty },
			{ Empty, Empty, Empty, Tree, Empty, Empty, Empty, Empty, Empty, Stone, Empty, Empty },
			{ Empty, Tree, Tree, Tree, Empty, Empty, Empty, Empty, Empty, Stone, Empty, Empty },
			{ Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty },
			{ Stone, Stone, Stone, Stone, Stone, Empty, Empty, Empty, Stone, Stone, Empty, Stone },
			{ Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty },
			{ Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty }
	};
	private static final int WIDTH = 12;
	private static final int HEIGHT = 13;

	public MapLevelDefault() {
		super(WIDTH, HEIGHT);
		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				set(i, j, level1[j][i]);
	}
}
