package fr.ubx.poo.ubomb.editor.model;

import fr.ubx.poo.ubomb.launcher.Entity;

public class GridRepoString implements GridRepo {
	final char EOL = 'x';

	@Override
	public Grid create(int height, int width) {
		Grid g = new Grid(width, height);
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				g.set(w, h, Entity.Empty);
			}
		}
		return g;
	}

	@Override
	public Grid load(String string) {
		int width = string.indexOf(EOL);
		if (width == -1)
			throw new GridException("No " + EOL + " found in string to load\n");
		if ((string.length()) % (width + 1) != 0)
			throw new GridException("Inconsistent width\n");
		int height = (string.length()) / (width + 1);
		Grid g = new Grid(width, height);
		int i = 0;
		int j = 0;
		for (char c : string.replaceAll(String.valueOf(EOL), "").toCharArray()) {
			g.set(i, j, Entity.fromCode(c));
			i++;
			if (i >= width) {
				j++;
				i = 0;
			}
		}
		return g;
	}

	@Override
	public String export(Grid grid) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < grid.getWidth(); i++) {
			for (int j = 0; j < grid.getHeight(); j++) {
				s.append(grid.get(i, j).getCode());
			}
			s.append(this.EOL);
		}
		return s.toString();
	}
}
