package fr.ubx.poo.ubomb.editor.model;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import fr.ubx.poo.ubomb.launcher.Entity;
import fr.ubx.poo.ubomb.view.ImageResource;

public class GridRepoStringRLE implements GridRepo, GridRepoIO {
	final char EOL = 'x';

	private static int toDigit(char c) {
		return c - 48;
	}

	@Override
	public Grid load(String string) {
		int firstEOL = string.indexOf(EOL);
		String strWithoutEOL = string.replaceAll(String.valueOf(EOL), "");
		int width = 0;
		for (int i = 0; i < firstEOL; i++) {
			if (Character.isDigit(string.charAt(i)))
				width += Character.getNumericValue(string.charAt(i)) - 1;
			else
				width++;
		}
		int height = 0;
		for (char c : string.toCharArray()) {
			if (c == EOL)
				height++;
		}
		int heightCpt = 0;
		int widthCpt = 0;
		char last = 0;
		Grid g = new Grid(width, height);
		for (char c : strWithoutEOL.toCharArray()) {
			if (widthCpt >= width) {
				widthCpt = 0;
				heightCpt++;
			}
			if (Character.isDigit(c)) {
				if (last == 0)
					throw new GridException("Incorrect coding\n");
				for (int k = Character.getNumericValue(c) - 1; k > 0; k--) {
					g.set(widthCpt++, heightCpt, Entity.fromCode(last));
				}

			} else {
				last = c;
				g.set(widthCpt++, heightCpt, Entity.fromCode(c));
			}
		}
		return g;
	}

	@Override
	public String export(Grid grid) {
		StringBuilder s = new StringBuilder();
		int cpt = 0;
		char last = 0;
		char actual = 0;
		for (int j = 0; j < grid.getHeight(); j++) {
			for (int i = 0; i < grid.getWidth(); i++) {
				actual = grid.get(i, j).getCode();
				if (cpt == 9 || last != actual) {
					if (cpt != 0)
						if (cpt < 3)
							for (int tmp = cpt; tmp > 0; tmp--)
								s.append(last);
						else
							s.append(last + String.valueOf(cpt));
					cpt = 1;
					last = actual;
				} else
					cpt++;
			}
			if (cpt != 0)
				if (cpt < 3) {
					for (int tmp = cpt; tmp > 0; tmp--)
						s.append(last);
					s.append(EOL);
				} else
					s.append(last + String.valueOf(cpt) + EOL);
			cpt = 0;
			last = 0;
		}
		return s.toString();
	}

	@Override
	public Grid create(int height, int width) {
		return new Grid(width, height);
	}

	@Override
	public Grid load(Reader in) throws IOException {
		StringBuilder s = new StringBuilder();
		int c = in.read();
		while (c != -1) {
			s.append((char) c);
			c = in.read();
		}
		return this.load(s.toString());
	}

	@Override
	public void export(Properties p, Writer out) throws IOException {
	}
}
