package fr.ubx.poo.ubomb.go.maps;

import static fr.ubx.poo.ubomb.launcher.Entity.*;

import fr.ubx.poo.ubomb.launcher.Entity;
import fr.ubx.poo.ubomb.launcher.MapLevel;

public class MapTestLoadFromFile extends MapLevel {
	private static final Entity[][] level1 = {
			{ Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Box, Box },
			{ Stone, Stone, Stone, Stone, Tree, Tree, Tree, Tree, Tree, BombRangeInc, BombRangeDec, Empty },
			{ BombNumberInc, BombNumberDec, BombNumberInc, BombNumberDec, Empty, Empty, Empty, Empty, Heart, Heart,
					Heart, Heart },
			{ Key, DoorPrevOpened, DoorNextOpened, DoorNextClosed, Empty, Empty, Empty, Empty, Empty, Empty,
					Empty, Princess }
	};
	private static final int WIDTH = level1[0].length;
	private static final int HEIGHT = level1.length;

	public MapTestLoadFromFile() {
		super(WIDTH, HEIGHT);
		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				set(i, j, level1[j][i]);
	}
}
