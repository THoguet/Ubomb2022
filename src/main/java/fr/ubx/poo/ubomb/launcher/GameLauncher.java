package fr.ubx.poo.ubomb.launcher;

import fr.ubx.poo.ubomb.game.Configuration;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Level;
import fr.ubx.poo.ubomb.game.Position;
import java.io.*;
import java.util.Properties;

public class GameLauncher {
	private GameLauncher() {
	}

	public static Game load() {
		Configuration configuration = new Configuration(new Position(0, 0), 3, 5, 4000, 5, 1000);
		return new Game(configuration, new Level(new MapLevelDefault()));
	}

	public static Game load(String path) {
		try {
			Reader in = new FileReader(path);
			Properties properties = new Properties();
			properties.load(in);
			boolean compression = Boolean.parseBoolean(properties.getProperty("compression", "false"));
			int nbLevels = Integer.parseInt(properties.getProperty("levels", "1"));
			String levels[] = new String[nbLevels];
			for (int i = 1; i <= nbLevels; i++) {
				levels[i - 1] = properties.getProperty("level" + i);
			}
			int playerLives = Integer.parseInt(properties.getProperty("playerLives", "3"));
			int monsterVelocity = Integer.parseInt(properties.getProperty("monsterVelocity", "5"));
			long playerInvisibilityTime = Long.parseLong(properties.getProperty("playerInvisibilityTime", "4000"));
			long monsterInvisibilityTime = Long.parseLong(properties.getProperty("monsterInvisibilityTime", "1000"));
			int bombBagCapacity = Integer.parseInt(properties.getProperty("bombBagCapacity", "3"));
			Position playerPos = new Position(Character.getNumericValue(properties.getProperty("player").charAt(0)),
					Character.getNumericValue(properties.getProperty("player").charAt(2)));
			in.close();
			Configuration config = new Configuration(playerPos, bombBagCapacity, playerLives, playerInvisibilityTime,
					monsterVelocity, monsterInvisibilityTime);
			// TODO read levelX with RLE and without
			return new Game(config, new Level(new MapLevelDefault()));
		} catch (IOException e) {
			return null;
		}
	}

}
