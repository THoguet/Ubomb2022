package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;

// Double dispatch visitor pattern
public interface TakeVisitor {
	// Key
	void take(Key key);

	void take(Princess p);

	void take(DoorNext door);

	void take(DoorPrev door);

	void take(BombNumberInc BNI);

	void take(BombNumberDec BND);

	void take(BombRangeInc BRI);

	void take(BombRangeDec BRD);

	void take(Heart heart);
}
