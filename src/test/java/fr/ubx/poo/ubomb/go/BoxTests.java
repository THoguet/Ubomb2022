package fr.ubx.poo.ubomb.go;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ubx.poo.ubomb.game.Configuration;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Level;
import fr.ubx.poo.ubomb.game.NonStaticObject;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.maps.MapLevelTest;

public class BoxTests {

	private Game game;
	private Player player;
	private NonStaticObject<Box> boxes;

	@BeforeEach
	public void initPlayer() {
		Configuration configuration = new Configuration(new Position(0, 0), 2, 5, 4000, 5, 1000);
		this.game = new Game(configuration, new Level(new MapLevelTest()));
		this.player = this.game.player();
		this.boxes = this.game.getBoxes();
	}

	@Test
	public void boxMove() {
		Box bTest = new Box(this.game, new Position(3, 1));
		Box bTest2 = new Box(this.game, new Position(3, 2));
		Box bTest3 = new Box(this.game, new Position(3, 4));
		this.boxes.add(bTest, 0);
		this.boxes.add(bTest2, 0);
		this.boxes.add(bTest3, 0);
		assertTrue(bTest.canMove(Direction.LEFT));
		assertTrue(bTest.canMove(Direction.UP));
		assertFalse(bTest.canMove(Direction.RIGHT));
		assertFalse(bTest.canMove(Direction.DOWN));
		this.player.setPosition(new Position(3, 3));
		assertFalse(this.player.canMove(Direction.UP));
		assertTrue(this.player.canMove(Direction.DOWN));
		this.player.requestMove(Direction.UP);
		this.player.update(0);
		assertEquals(this.player.getPosition(), new Position(3, 3));
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertEquals(this.player.getPosition(), new Position(3, 4));
		assertEquals(bTest3.getPosition(), new Position(3, 5));
	}
}
