package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class BombRangeDec extends Bonus {
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

    @Override
    public boolean equals(Object arg0) {
        return arg0 instanceof BombRangeDec && super.equals(arg0);
    }
}
