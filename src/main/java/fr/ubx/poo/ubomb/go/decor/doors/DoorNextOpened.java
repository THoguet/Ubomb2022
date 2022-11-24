package fr.ubx.poo.ubomb.go.decor.doors;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class DoorNextOpened extends Doors {

	public DoorNextOpened(Position position) {
		super(position);
	}

	@Override
	public void takenBy(Player player) {
		player.take(this);
	}

}
