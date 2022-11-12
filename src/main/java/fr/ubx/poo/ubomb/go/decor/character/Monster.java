package fr.ubx.poo.ubomb.go.decor.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.TakeVisitor;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.decor.bonus.*;

//TODO
//fusion Monster + Player (movable)
//princess reste seule car =/= movable
public class Monster extends Character implements Takeable {
    public Monster(Position position) {
        super(position);
    }

    public Monster(Game game, Position position) {
        super(game, position);
    }

    public boolean canMove(Direction direction) {
        return true;
    }

    public void doMove(Direction direction) {
        // TODO
    }

    @Override
    public void takenBy(Player player) {
        player.take(this);
    }

}