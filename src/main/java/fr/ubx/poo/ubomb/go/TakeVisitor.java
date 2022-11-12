package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.character.Monster;
import fr.ubx.poo.ubomb.go.decor.character.Princess;

// Double dispatch visitor pattern
public interface TakeVisitor {
    // Key
    default void take(Key key) {
    }

    default void take(Princess p) {
    }

    default void take(Monster m) {
    }

}
