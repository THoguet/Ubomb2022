/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.character.Chara;

public interface Takeable {
	default void takenBy(Player player) {
	}

	default void takenBy(Monster monster) {
	}

	default void takenBy(Chara character) {
	}
}
