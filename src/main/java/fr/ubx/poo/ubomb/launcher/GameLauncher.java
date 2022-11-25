package fr.ubx.poo.ubomb.launcher;

import fr.ubx.poo.ubomb.game.Configuration;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Grid;
import fr.ubx.poo.ubomb.game.Monsters;
import fr.ubx.poo.ubomb.game.Level;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Monster;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class GameLauncher {
	private GameLauncher() {
	}

	public static Game load() {
		Configuration configuration = new Configuration(new Position(0, 0), 3, 5, 4000, 5, 1000);
		return new Game(configuration, new Level(new MapLevelDefault()));
	}

	private static MapLevel RLE_Decode(String string, boolean onlyMonster) {
		char EOL = 'x';
		int firstEOL = string.indexOf(EOL);
		String strWithoutEOL = string.replaceAll(String.valueOf(EOL), "");
		int width = 0;
		for (int i = 0; i < firstEOL; i++) {
			if (Character.isDigit(string.charAt(i)))
				width += Character.getNumericValue(string.charAt(i)) - 1;
			else
				width++;
		}
		int height = 0;
		for (char c : string.toCharArray()) {
			if (c == EOL)
				height++;
		}
		int heightCpt = 0;
		int widthCpt = 0;
		char last = 0;
		MapLevel map = new MapLevel(width, height);
		for (char c : strWithoutEOL.toCharArray()) {
			if (widthCpt >= width) {
				widthCpt = 0;
				heightCpt++;
			}
			if (Character.isDigit(c)) {
				if (last == 0)
					throw new GridException("Incorrect coding\n");
				if (!onlyMonster && Entity.fromCode(last) == Entity.Monster) {
					for (int cpt = 0; cpt < Character.getNumericValue(c) - 1; cpt++) {
						widthCpt++;
					}
					continue;
				}
				for (int k = Character.getNumericValue(c) - 1; k > 0; k--) {
					map.set(widthCpt++, heightCpt, Entity.fromCode(last));
				}
			} else {
				last = c;
				if (!onlyMonster && Entity.fromCode(c) == Entity.Monster) {
					widthCpt++;
					continue;
				}
				map.set(widthCpt++, heightCpt, Entity.fromCode(c));
			}
		}
		return map;
	}

	public static Game load(String path) {
		try {
			Reader in = new FileReader(path);
			Properties properties = new Properties();
			properties.load(in);
			boolean compression = Boolean.parseBoolean(properties.getProperty("compression", "false"));
			int nbLevel = Integer.parseInt(properties.getProperty("levels", "1"));
			String levels[] = new String[nbLevel];
			for (int i = 1; i <= nbLevel; i++) {
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
			Grid[] grids = new Grid[nbLevel];
			MapLevel[] monstersPosLeveled = new MapLevel[nbLevel];
			for (int i = 0; i < nbLevel; i++) {
				grids[i] = new Level(RLE_Decode(levels[i], false));
				monstersPosLeveled[i] = RLE_Decode(levels[i], true);
			}
			Game gameRet = new Game(config, grids);
			Monsters monsters = gameRet.getMonsters();
			for (int level = 0; level < nbLevel; level++) {
				for (int i = 0; i < monstersPosLeveled[level].width(); i++) {
					for (int j = 0; j < monstersPosLeveled[level].height(); j++) {
						if (monstersPosLeveled[level].get(i, j) == Entity.Monster)
							monsters.add(new Monster(gameRet, new Position(i, j)), level);
					}
				}
			}
			return gameRet;
		} catch (IOException e) {
			return null;
		}
	}

}
