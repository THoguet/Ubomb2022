package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Chara;
import fr.ubx.poo.ubomb.go.character.Player;

public class Heart extends Bonus {
    public Heart(Position position) {
        super(position);
    }

    @Override
    public void explode() {
        remove();
    }

    @Override
    public void takenBy(Chara c) {
        c.take(this);
    }

    @Override
    public boolean walkableBy(Chara c) {
        return c.walk(this);
    }

    @Override
    public boolean equals(Object arg0) {
        return arg0 instanceof Heart && super.equals(arg0);
    }
}
