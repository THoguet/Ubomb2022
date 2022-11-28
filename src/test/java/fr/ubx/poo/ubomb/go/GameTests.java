package fr.ubx.poo.ubomb.go;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import fr.ubx.poo.ubomb.game.Configuration;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Level;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.Tree;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.maps.MapLevelTest;
import fr.ubx.poo.ubomb.go.maps.MapTestLoadFromFile;
import fr.ubx.poo.ubomb.launcher.GameLauncher;
import fr.ubx.poo.ubomb.launcher.MapLevel;

public class GameTests {

	@Test
	public void testDecor() {
		MapLevel mapTest = new MapLevelTest();
		Configuration configuration = new Configuration(new Position(0, 0), 3, 5, 4000, 5, 1000);
		Level copyL = new Level(mapTest);
		Level level = new Level(mapTest);
		Game game = new Game(configuration, copyL);
		for (int i = 0; i < game.grid().height(); i++) {
			assertTrue(game.grid().get(new Position(0, i)) == null, i + ",0 isn't null");
			Decor d = level.get(new Position(1, i));
			assertTrue(game.grid().get(new Position(1, i)).equals(d), "0," + i + " isn't " + d);
		}
	}

	@Test
	public void testLoader() {
		String filePath = new File("").getAbsolutePath();
		Game gToTest = GameLauncher.load(filePath + "/world/test.properties");
		assertNotNull(gToTest);
		MapLevel mapTest = new MapTestLoadFromFile();
		Configuration configuration = new Configuration(new Position(0, 0), 3, 30, 4000, 5, 10000);
		Level level = new Level(mapTest);
		Game gVerif = new Game(configuration, level);
		// config
		assertTrue(gToTest.configuration().bombBagCapacity() == gVerif.configuration().bombBagCapacity());
		assertTrue(
				gToTest.configuration().monsterInvisibilityTime() == gVerif.configuration().monsterInvisibilityTime());
		assertTrue(gToTest.configuration().monsterVelocity() == gVerif.configuration().monsterVelocity());
		assertTrue(gToTest.configuration().playerInvisibilityTime() == gVerif.configuration().playerInvisibilityTime());
		assertTrue(gToTest.configuration().playerLives() == gVerif.configuration().playerLives());
		assertTrue(gToTest.configuration().playerPosition().equals(gVerif.configuration().playerPosition()));
		// map
		int gtvHeight = gToTest.grid().height();
		int gtvWidth = gToTest.grid().width();
		assertTrue(gtvHeight == gVerif.grid().height(), gtvHeight + " != " + gVerif.grid().height());
		assertTrue(gtvWidth == gVerif.grid().width(), gtvWidth + " != " + gVerif.grid().width());
		for (int i = 0; i < gtvHeight; i++) {
			for (int j = 0; j < gtvWidth; j++) {
				assertEquals(gVerif.grid().get(new Position(i, j)), gToTest.grid().get(new Position(i, j)));
			}
		}
		// monsters
		assertTrue(gToTest.getMonsters().getObjects(0).size() == 7);
		boolean exist83 = false;
		boolean exist43 = false;
		for (Monster m : gToTest.getMonsters().getObjects(0)) {
			if (m.getPosition().equals(new Position(8, 3)))
				exist83 = true;
			if (m.getPosition().equals(new Position(4, 3)))
				exist43 = true;
		}
		assertTrue(exist83 && exist43);
		assertTrue(gToTest.player().getPosition().equals(gToTest.configuration().playerPosition()));
		gToTest.addLevel(1);
		assertTrue(gToTest.grid().get(new Position(7, 0)).equals(new Tree(new Position(7, 0))));
		assertTrue(gToTest.grid().get(new Position(1, 1)).equals(new DoorNext(new Position(1, 1), false)));
	}
}
