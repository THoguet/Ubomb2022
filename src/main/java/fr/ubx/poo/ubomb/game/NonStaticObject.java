package fr.ubx.poo.ubomb.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.ubx.poo.ubomb.go.GameObject;

public class NonStaticObject<T extends GameObject> {
	private final Map<Integer, List<T>> objectsByLevel = new LinkedHashMap<>();

	public int isThereObject(Position pos, int level) {
		if (!this.objectsByLevel.containsKey(Integer.valueOf(level)))
			return -1;
		for (int i = 0; i < this.objectsByLevel.get(Integer.valueOf(level)).size(); i++) {
			if (this.objectsByLevel.get(Integer.valueOf(level)).get(i).getPosition().equals(pos))
				return i;
		}
		return -1;
	}

	public void add(T m, int level) {
		if (!this.objectsByLevel.containsKey(Integer.valueOf(level)))
			this.objectsByLevel.put(Integer.valueOf(level), new ArrayList<>());
		this.objectsByLevel.get(Integer.valueOf(level)).add(m);
	}

	public List<T> getObjects(int level) {
		if (!this.objectsByLevel.containsKey(Integer.valueOf(level)))
			this.objectsByLevel.put(Integer.valueOf(level), new ArrayList<>());
		return this.objectsByLevel.get(Integer.valueOf(level));
	}

	public List<T> getObjects() {
		List<T> ret = new ArrayList<>();
		for (Entry<Integer, List<T>> t : this.objectsByLevel.entrySet()) {
			ret.addAll(t.getValue());
		}
		return ret;
	}

}
