package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.Takeable;

public class BombRangeDec extends Bonus implements Takeable {
    public BombRangeDec(Position position) {
        super(position);
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
