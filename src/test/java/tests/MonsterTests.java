package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ubx.poo.ubomb.game.Configuration;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Level;
import fr.ubx.poo.ubomb.game.NonStaticObject;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.bonus.BombNumberDec;
import fr.ubx.poo.ubomb.go.decor.bonus.BombNumberInc;
import fr.ubx.poo.ubomb.go.decor.bonus.BombRangeDec;
import fr.ubx.poo.ubomb.go.decor.bonus.BombRangeInc;
import fr.ubx.poo.ubomb.go.decor.bonus.Heart;
import fr.ubx.poo.ubomb.go.decor.bonus.Key;
import maps.MapLevelTest;

public class MonsterTests {

	private Game game;
	private Monster monster;
	private NonStaticObject<Box> boxes;

	@BeforeEach
	public void init() {
		Configuration configuration = new Configuration(new Position(0, 0), 2, 5, 4000, 5, 1000);
		this.game = new Game(configuration, new Level(new MapLevelTest()));
		this.monster = new Monster(game, new Position(0, 0), 0);
		this.boxes = this.game.getBoxes();
	}

	@Test
	public void moveMonster() {
		Box bTest = new Box(this.game, new Position(3, 1));
		Box bTest2 = new Box(this.game, new Position(3, 2));
		Box bTest3 = new Box(this.game, new Position(3, 4));
		this.boxes.add(bTest, 0);
		this.boxes.add(bTest2, 0);
		this.boxes.add(bTest3, 0);
		int i = 0, j = 0;
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		this.monster.requestMove(Direction.UP);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.UP));
		assertTrue(this.monster.getPosition().equals(new Position(i, j)));
		this.monster.requestMove(Direction.LEFT);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.LEFT));
		assertTrue(this.monster.getPosition().equals(new Position(i, j)));
		this.monster.requestMove(Direction.RIGHT);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.RIGHT));
		assertTrue(this.monster.getPosition().equals(new Position(i, j)));
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		this.monster.requestMove(Direction.RIGHT);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.RIGHT));
		assertTrue(this.monster.getPosition().equals(new Position(i, j)));
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		this.monster.requestMove(Direction.RIGHT);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.RIGHT));
		assertTrue(this.monster.getPosition().equals(new Position(i, j)));
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		this.monster.requestMove(Direction.RIGHT);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.RIGHT));
		assertTrue(this.monster.getPosition().equals(new Position(++i, j)));
		assertTrue(this.game.grid().get(new Position(i, j)) instanceof BombNumberDec);
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.game.grid().get(new Position(i, j)) instanceof BombNumberDec);
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.game.grid().get(new Position(i, j)) instanceof BombNumberInc);
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.game.grid().get(new Position(i, j)) instanceof BombRangeInc);
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.game.grid().get(new Position(i, j)) instanceof BombRangeDec);
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.game.grid().get(new Position(i, j)) instanceof BombRangeDec);
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.game.grid().get(new Position(i, j)) instanceof Heart);
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.game.grid().get(new Position(i, j)) instanceof Key);
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, j)));
		this.monster.requestMove(Direction.LEFT);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.LEFT));
		assertTrue(this.monster.getPosition().equals(new Position(--i, j)));
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		this.monster.requestMove(Direction.RIGHT);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.RIGHT));
		assertTrue(this.monster.getPosition().equals(new Position(i, j)));
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		this.monster.requestMove(Direction.RIGHT);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.RIGHT));
		assertTrue(this.monster.getPosition().equals(new Position(i, j)));
		this.monster.requestMove(Direction.DOWN);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.DOWN));
		assertTrue(this.monster.getPosition().equals(new Position(i, ++j)));
		this.monster.requestMove(Direction.RIGHT);
		this.monster.update(0);
		assertTrue(this.monster.getDirection().equals(Direction.RIGHT));
		assertTrue(this.monster.getPosition().equals(new Position(i, j)));
		this.monster.setPosition(new Position(3, 5));
		this.monster.requestMove(Direction.UP);
		this.monster.update(0);
		assertTrue(this.monster.getPosition().equals(new Position(3, 5)));
		assertTrue(this.boxes.getObjects().contains(new Box(game, new Position(3, 4))));
		this.monster.setPosition(new Position(3, 4));
		this.monster.requestMove(Direction.UP);
		this.monster.update(0);
		assertTrue(this.monster.getPosition().equals(new Position(3, 3)));
		assertTrue(this.boxes.getObjects().contains(new Box(game, new Position(3, 2))));
		assertTrue(this.boxes.getObjects().contains(new Box(game, new Position(3, 1))));
	}
}