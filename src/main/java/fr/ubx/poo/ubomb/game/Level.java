package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.doors.DoorNext;
import fr.ubx.poo.ubomb.go.decor.doors.DoorPrev;
import fr.ubx.poo.ubomb.go.decor.*;
import fr.ubx.poo.ubomb.launcher.Entity;
import fr.ubx.poo.ubomb.launcher.MapLevel;

import java.util.*;

public class Level implements Grid {

	private final int width;

	private final int height;

	private final Map<Position, Decor> elements = new HashMap<>();

	public Level(MapLevel entities) {
		this.width = entities.width();
		this.height = entities.height();

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				Position position = new Position(i, j);
				Entity entity = entities.get(i, j);
				if (entity == null)
					continue;
				switch (entity) {
					case Stone:
						elements.put(position, new Stone(position));
						break;
					case Tree:
						elements.put(position, new Tree(position));
						break;
					case Key:
						elements.put(position, new Key(position));
						break;
					case Princess:
						elements.put(position, new Princess(position));
						break;
					case DoorNextClosed:
						elements.put(position, new DoorNext(position, false));
						break;
					case DoorPrevOpened:
						elements.put(position, new DoorPrev(position));
						break;
					case DoorNextOpened:
						elements.put(position, new DoorNext(position, true));
						break;
					case BombRangeInc:
						elements.put(position, new BombRangeInc(position));
						break;
					case BombRangeDec:
						elements.put(position, new BombRangeDec(position));
						break;
					case BombNumberInc:
						elements.put(position, new BombNumberInc(position));
						break;
					case BombNumberDec:
						elements.put(position, new BombNumberDec(position));
						break;
					case Heart:
						elements.put(position, new Heart(position));
						break;
					case Box:
					case Monster:
					case Empty:
						break;
					default:
						throw new RuntimeException("EntityCode " + entity.name() + " not processed");
				}
			}
	}

	@Override
	public int width() {
		return this.width;
	}

	@Override
	public int height() {
		return this.height;
	}

	public Decor get(Position position) {
		return elements.get(position);
	}

	@Override
	public void remove(Position position) {
		elements.remove(position);
	}

	public Collection<Decor> values() {
		return elements.values();
	}

	@Override
	public boolean inside(Position position) {
		return position.x() >= 0 && position.x() < this.width && position.y() >= 0 && position.y() < this.height;
	}

	@Override
	public void set(Position position, Decor decor) {
		if (!inside(position))
			throw new IllegalArgumentException("Illegal Position");
		if (decor != null)
			elements.put(position, decor);
	}

}
