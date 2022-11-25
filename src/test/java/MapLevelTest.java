import static fr.ubx.poo.ubomb.launcher.Entity.*;

import fr.ubx.poo.ubomb.launcher.Entity;
import fr.ubx.poo.ubomb.launcher.MapLevel;

public class MapLevelTest extends MapLevel {
	private static final Entity[][] level1 = {
			{ Empty, Box },
			{ Empty, Stone },
			{ Empty, Tree },
			{ Empty, BombNumberDec },
			{ Empty, BombNumberInc },
			{ Empty, BombRangeDec },
			{ Empty, BombRangeInc },
			{ Empty, Heart },
			{ Empty, Key },
			{ Empty, Princess },
			{ Empty, DoorNextClosed },
			{ Empty, DoorNextOpened },
			{ Empty, DoorPrevOpened }
	};
	private static final int WIDTH = level1[0].length;
	private static final int HEIGHT = level1.length;

	public MapLevelTest() {
		super(WIDTH, HEIGHT);
		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				set(i, j, level1[j][i]);
	}
}
