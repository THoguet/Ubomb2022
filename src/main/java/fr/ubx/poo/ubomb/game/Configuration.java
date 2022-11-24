package fr.ubx.poo.ubomb.game;

public record Configuration(Position playerPosition, int bombBagCapacity, int playerLives, long playerInvisibilityTime,
		int monsterVelocity, long monsterInvisibilityTime) {
}
/*
if lives == 0 -> Lives = 5
if NbBombMax == 0 -> NbBombMax = 3
if availableBombs == 0 -> availableBombs = NbBombMax
 */