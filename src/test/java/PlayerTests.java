import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ubx.poo.ubomb.game.Configuration;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Level;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.launcher.MapException;

public class PlayerTests {

	private Game game;
	private Player player;

	@BeforeEach
	public void initPlayer() {
		Configuration configuration = new Configuration(new Position(0, 0), 2, 5, 4000, 5, 1000);
		this.game = new Game(configuration, new Level(new MapLevelTest()));
		this.player = game.player();
	}

	@Test
	public void playerMoves() {
		int i = 0, j = 0;
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		this.player.requestMove(Direction.UP);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.UP));
		assertTrue(this.player.getPosition().equals(new Position(i, j)));
		this.player.requestMove(Direction.LEFT);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.LEFT));
		assertTrue(this.player.getPosition().equals(new Position(i, j)));
		this.player.requestMove(Direction.RIGHT);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.RIGHT));
		assertTrue(this.player.getPosition().equals(new Position(i, j)));
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		this.player.requestMove(Direction.RIGHT);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.RIGHT));
		assertTrue(this.player.getPosition().equals(new Position(i, j)));
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		this.player.requestMove(Direction.RIGHT);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.RIGHT));
		assertTrue(this.player.getPosition().equals(new Position(i, j)));
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		this.player.requestMove(Direction.RIGHT);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.RIGHT));
		assertTrue(this.player.getPosition().equals(new Position(++i, j)));
		assertTrue(this.player.getNbBombsMax() == 1);
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.player.getNbBombsMax() == 1);
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.player.getNbBombsMax() == 2);
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.player.getBombRange() == 2);
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.player.getBombRange() == 1);
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.player.getBombRange() == 1);
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.player.getLives() == 6);
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.player.getKeys() == 1);
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.player.tookPrincess());
		this.player.requestMove(Direction.DOWN);
		this.player.update(0);
		assertTrue(this.player.getDirection().equals(Direction.DOWN));
		assertTrue(this.player.getPosition().equals(new Position(i, ++j)));
		assertTrue(this.player.game.getLevel() == 0);
		this.player.requestMove(Direction.DOWN);
		try {
			this.player.update(0);
		} catch (MapException e) {
			return;
		}
		assertTrue(false, "this assert shouldn't be executed");
	}

}
