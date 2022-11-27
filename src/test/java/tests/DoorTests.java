package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ubx.poo.ubomb.game.Configuration;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Level;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;
import fr.ubx.poo.ubomb.launcher.MapException;
import maps.MapLevelTest;

public class DoorTests {

	private Game game;
	private Player player;
	private DoorNext doorNextClosed;
	private DoorNext doorNextOpened;
	private DoorPrev doorprev;

	@BeforeEach
	public void init() {
		Configuration configuration = new Configuration(new Position(0, 0), 2, 5, 4000, 5, 1000);
		this.game = new Game(configuration, new Level(new MapLevelTest()));
		this.player = game.player();
		this.doorNextClosed = (DoorNext) this.game.grid().get(new Position(1, 12));
		this.doorNextOpened = (DoorNext) this.game.grid().get(new Position(1, 13));
		this.doorprev = (DoorPrev) this.game.grid().get(new Position(1, 14));
	}

	private void verifDoors(boolean nextClosed, boolean nextOpened) {
		assertTrue(this.doorNextClosed.isOpen() == nextClosed);
		assertTrue(this.doorNextOpened.isOpen() == nextOpened);
		assertTrue(this.doorprev.isOpen());
	}

	@Test
	public void openDoor() {
		verifDoors(false, true);
		this.player.openDoor();
		verifDoors(false, true);
		for (Direction d : Direction.values()) {
			Position positionPlayer = d.opposit().nextPosition(this.doorNextClosed.getPosition());
			this.player.setPosition(positionPlayer);
			this.player.requestMove(d);
			this.player.openDoor();
			verifDoors(false, true);
			this.player.addKeys(1);
			this.player.openDoor();
			verifDoors(true, true);
			this.doorNextClosed.setOpen(false);
			verifDoors(false, true);
		}
		this.player.setPosition(this.doorNextClosed.getPosition());
		this.player.openDoor();
		verifDoors(false, true);
		this.player.setPosition(this.player.getDirection().opposit().nextPosition(this.doorNextClosed.getPosition()));
		this.player.addKeys(1);
		this.player.openDoor();
		this.player.requestMove(this.player.getDirection());
		try {
			this.player.update(0);
		} catch (MapException e) {
			return;
		}
		assertTrue(false, "this assert shouldn't be executed");
	}
}
