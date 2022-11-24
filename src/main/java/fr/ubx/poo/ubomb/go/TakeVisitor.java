package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNextOpened;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrevOpened;

// Double dispatch visitor pattern
public interface TakeVisitor {
	// Key
	void take(Key key);

	void take(Princess p);

	void take(DoorNextOpened door);

	void take(DoorPrevOpened door);

}
