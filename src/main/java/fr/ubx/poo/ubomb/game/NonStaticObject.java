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
		if (!this.objectsByLevel.containsKey(level))
			return -1;
		for (int i = 0; i < this.getObjects(level).size(); i++) {
			if (this.getObjects(level).get(i).getPosition().equals(pos))
				return i;
		}
		return -1;
	}

	public void add(T m, int level) {
		if (!this.objectsByLevel.containsKey(level))
			this.objectsByLevel.put(level, new ArrayList<>());
		this.objectsByLevel.get(level).add(m);
	}

	public List<T> getObjects(int level) {
		if (!this.objectsByLevel.containsKey(level))
			this.objectsByLevel.put(level, new ArrayList<>());
		List<T> ret = this.objectsByLevel.get(level);
		ret.removeIf(GameObject::isDeleted);
		return ret;
	}

	public List<T> getObjects() {
		List<T> ret = new ArrayList<>();
		for (Entry<Integer, List<T>> t : this.objectsByLevel.entrySet()) {
			ret.addAll(t.getValue());
		}
		ret.removeIf(GameObject::isDeleted);
		return ret;
	}

}
