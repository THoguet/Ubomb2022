package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.Stone;
import fr.ubx.poo.ubomb.go.decor.Tree;
import fr.ubx.poo.ubomb.go.decor.bonus.BombNumberDec;
import fr.ubx.poo.ubomb.go.decor.bonus.BombNumberInc;
import fr.ubx.poo.ubomb.go.decor.bonus.BombRangeDec;
import fr.ubx.poo.ubomb.go.decor.bonus.BombRangeInc;
import fr.ubx.poo.ubomb.go.decor.bonus.Bonus;
import fr.ubx.poo.ubomb.go.decor.bonus.Heart;
import fr.ubx.poo.ubomb.go.decor.bonus.Key;
import fr.ubx.poo.ubomb.go.decor.bonus.Princess;
import fr.ubx.poo.ubomb.go.decor.doors.Door;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;

// Double dispatch visitor pattern
public interface WalkVisitor {
	// Key
	default boolean walk(Box b) {
		return false;
	}

	default boolean walk(Tree t) {
		return false;
	}

	default boolean walk(Stone s) {
		return false;
	}

	default boolean walk(DoorPrev d) {
		return true;
	}

	default boolean walk(DoorNext d) {
		return d.isOpen();
	}

	default boolean walk(BombNumberDec b) {
		return true;
	}

	default boolean walk(BombNumberInc b) {
		return true;
	}

	default boolean walk(BombRangeDec b) {
		return true;
	}

	default boolean walk(BombRangeInc b) {
		return true;
	}

	default boolean walk(Heart b) {
		return true;
	}

	default boolean walk(Key b) {
		return true;
	}

	default boolean walk(Princess b) {
		return true;
	}
}
