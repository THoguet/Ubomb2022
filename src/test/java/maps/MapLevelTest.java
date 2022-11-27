package maps;

import static fr.ubx.poo.ubomb.launcher.Entity.*;

import fr.ubx.poo.ubomb.launcher.Entity;
import fr.ubx.poo.ubomb.launcher.MapLevel;

public class MapLevelTest extends MapLevel {
	private static final Entity[][] level1 = {
			{ Empty, Stone, Empty, Empty },
			{ Empty, Tree, Empty, Box },
			{ Empty, Stone, Empty, Box },
			{ Empty, BombNumberDec, Empty, Empty },
			{ Empty, BombNumberDec, Empty, Box },
			{ Empty, BombNumberInc, Empty, Empty },
			{ Empty, BombRangeInc, Empty, Empty },
			{ Empty, BombRangeDec, Empty, Empty },
			{ Empty, BombRangeDec, Empty, Empty },
			{ Empty, Heart, Empty, Empty },
			{ Empty, Key, Empty, Empty },
			{ Empty, Princess, Empty, Empty },
			{ Empty, DoorNextClosed, Empty, Empty },
			{ Empty, DoorNextOpened, Empty, Empty },
			{ Empty, DoorPrevOpened, Empty, Empty }
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
