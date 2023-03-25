package fr.ubx.poo.ubomb.editor.model;

public interface GridRepo {
	Grid load(String string);

	String export(Grid grid);

	Grid create(int height, int width);
}
