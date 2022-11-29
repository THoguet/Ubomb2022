package fr.ubx.poo.ubomb.editor.model;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

public interface GridRepoIO {
	Grid load(Reader in) throws IOException;

	void export(Properties p, Writer ou) throws IOException;
}
