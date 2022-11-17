package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class Princess extends Bonus {

	public Princess(Position position) {
		super(position);
	}

	@Override
	public boolean walkableBy(Player player) {
		return true;
	}

	@Override
	public void explode() {
		remove();
	}

	@Override
	public void takenBy(Player player) {
		player.take(this);
	}
}
