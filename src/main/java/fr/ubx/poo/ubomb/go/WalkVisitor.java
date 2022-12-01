package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Stone;
import fr.ubx.poo.ubomb.go.decor.Tree;
import fr.ubx.poo.ubomb.go.decor.bonus.Bonus;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;

// Double dispatch visitor pattern
public interface WalkVisitor {
	// Key
	default boolean walk(Box b) {
		return false;
	}

	default boolean walk(Stone s) {
		return false;
	}

	default boolean walk(Tree t) {
		return false;
	}

	default boolean walk(DoorNext d) {
		return d.isOpen();
	}

	default boolean walk(DoorPrev d) {
		return true;
	}

	default boolean walk(Bonus b) {
		return true;
	}
}
