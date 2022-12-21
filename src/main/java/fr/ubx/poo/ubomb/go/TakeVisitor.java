package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;

// Double dispatch visitor pattern
public interface TakeVisitor {
	// Key
	default void take(Key key) {
	}

	default void take(Princess p) {
	}

	default void take(DoorNext door) {
	}

	default void take(DoorPrev door) {
	}

	default void take(BombNumberInc BNI) {
	}

	default void take(BombNumberDec BND) {
	}

	default void take(BombRangeInc BRI) {
	}

	default void take(BombRangeDec BRD) {
	}

	default void take(Heart heart) {
	}
}
