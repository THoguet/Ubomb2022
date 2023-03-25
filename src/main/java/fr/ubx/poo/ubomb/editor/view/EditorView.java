package fr.ubx.poo.ubomb.editor.view;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import fr.ubx.poo.ubomb.editor.model.Grid;
import fr.ubx.poo.ubomb.editor.model.GridRepoString;
import fr.ubx.poo.ubomb.editor.model.GridRepoStringRLE;
import fr.ubx.poo.ubomb.editor.model.GridView;
import fr.ubx.poo.ubomb.engine.GameEngine;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.launcher.GameLauncher;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditorView extends BorderPane {
	private final Stage stage;
	private Grid grid = new Grid(0, 0);
	private final PickerView pickerView;
	private final Clipboard clipboard = Clipboard.getSystemClipboard();
	private final ClipboardContent clipboardContent = new ClipboardContent();

	private final Properties pro = new Properties();

	private void writeToFile(File file) {
		try {
			FileWriter w = new FileWriter(file);
			this.pro.store(w, "");
			w.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public EditorView(Stage stage) {
		pro.setProperty("compression", "true");
		pro.setProperty("levels", "0");
		pro.setProperty("player", "0x0");
		this.stage = stage;
		GridRepoString gridRepoString = new GridRepoString();
		GridRepoStringRLE gridRepoStringRLE = new GridRepoStringRLE();

		// Tile picker
		this.pickerView = new PickerView();
		this.setRight(pickerView);

		FileChooser fileChooser = new FileChooser();

		// Create menu
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");

		MenuItem newItem = new MenuItem("New map");
		MenuItem loadItemS = new MenuItem("Load from string");
		MenuItem loadItemSZ = new MenuItem("Load from compressed string");
		MenuItem loadItemF = new MenuItem("Import from file");
		MenuItem addToProperties = new MenuItem("Add grid to properties file");
		MenuItem setPlayerLives = new MenuItem("Set player lives");
		MenuItem setBombBagCapacity = new MenuItem("Set bomb Bag Capacity");
		MenuItem setMonsterVelo = new MenuItem("Set monster velocity");
		MenuItem setPlayerInvin = new MenuItem("Set Player invincibility");
		MenuItem setMonsterInvin = new MenuItem("Set Monster invincibility");
		MenuItem setPlayerSpawn = new MenuItem("Set player spawn");
		MenuItem playProp = new MenuItem("Play properties file");
		MenuItem deleteLastLevel = new MenuItem("Delete last level");
		MenuItem exportItemF = new MenuItem("Export properties as file");
		MenuItem exportItemS = new MenuItem("Export as string");
		MenuItem exportItemSZ = new MenuItem("Export as compressed string");
		MenuItem exitItem = new MenuItem("Exit");

		exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
		fileMenu.getItems().addAll(newItem, new SeparatorMenuItem(), loadItemS, loadItemSZ, loadItemF,
				new SeparatorMenuItem(), addToProperties, setPlayerLives, setBombBagCapacity, setMonsterVelo,
				setPlayerInvin, setMonsterInvin, setPlayerSpawn, playProp, deleteLastLevel, exportItemF,
				new SeparatorMenuItem(), exportItemSZ, exportItemS, new SeparatorMenuItem(), exitItem);
		menuBar.getMenus().add(fileMenu);
		this.setTop(menuBar);
		// New map
		newItem.setOnAction(e -> {
			Form form = new Form(stage, "Size of the map : width x height");
			String[] parts = form.getText().replaceAll("\\s+", "").split("x");
			if (parts.length != 2)
				return;
			try {
				int x = Integer.parseInt(parts[0]);
				int y = Integer.parseInt(parts[1]);
				this.grid = gridRepoString.create(x, y);
				updateGrid(grid);
			} catch (NumberFormatException ignored) {
			}
		});

		setPlayerLives.setOnAction(e -> {
			Form form = new Form(stage, "Player lives :");
			int value = Integer.parseInt(form.getText());
			this.pro.setProperty("playerLives", String.valueOf(value));
		});

		setMonsterVelo.setOnAction(e -> {
			Form form = new Form(stage, "Monster velocity (s):");
			int value = Integer.parseInt(form.getText());
			this.pro.setProperty("monsterVelocity", String.valueOf(value));
		});

		setPlayerInvin.setOnAction(e -> {
			Form form = new Form(stage, "Player Invincibility time (ms):");
			int value = Integer.parseInt(form.getText());
			this.pro.setProperty("playerInvisibilityTime", String.valueOf(value));
		});

		setMonsterInvin.setOnAction(e -> {
			Form form = new Form(stage, "Monster Invincibility time (ms):");
			int value = Integer.parseInt(form.getText());
			this.pro.setProperty("monsterInvisibilityTime", String.valueOf(value));
		});

		setBombBagCapacity.setOnAction(e -> {
			Form form = new Form(stage, "Bomb bad capacity:");
			int value = Integer.parseInt(form.getText());
			this.pro.setProperty("bombBagCapacity", String.valueOf(value));
		});

		setPlayerSpawn.setOnAction(e -> {
			Form form = new Form(stage, "Player spawn (IxJ) :");
			String[] parts = form.getText().replaceAll("\\s+", "").split("x");
			if (parts.length != 2)
				return;
			try {
				Integer.parseInt(parts[0]);
				Integer.parseInt(parts[1]);
				pro.setProperty("player", form.getText().replaceAll("\\s+", ""));
			} catch (NumberFormatException ignored) {
			}
		});

		deleteLastLevel.setOnAction(e -> {
			int levels = Integer.parseInt(pro.getProperty("levels"));
			pro.setProperty("levels", String.valueOf(--levels));
		});

		// Load from file
		loadItemF.setOnAction(e -> {
			File file = fileChooser.showOpenDialog(stage);
			if (file != null) {
				try {
					FileReader r = new FileReader(file);
					this.grid = gridRepoStringRLE.load(r);
					updateGrid(grid);
					r.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		addToProperties.setOnAction(e -> {
			int levels = Integer.parseInt(pro.getProperty("levels"));
			pro.setProperty("levels", String.valueOf(++levels));
			pro.setProperty("level" + levels, gridRepoStringRLE.export(grid));
		});

		playProp.setOnAction(e -> {
			File file = new File("tmp.properties");
			writeToFile(file);
			Stage stagePlay = new Stage();
			Game game = GameLauncher.load(file.getPath());
			file.delete();
			GameEngine engine = new GameEngine(game, stagePlay);
			engine.start();
		});

		// Export to file
		exportItemF.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			fc.setInitialFileName(new SimpleDateFormat("dd_MM_yyyy_HH_mm").format(new Date()) + ".properties");
			File file = fc.showSaveDialog(stage);
			if (file != null) {
				writeToFile(file);
			}
		});
		// Load from String
		loadItemS.setOnAction(e -> {
			Form form = new Form(stage, "Input string");
			this.grid = gridRepoString.load(form.getText());
			updateGrid(grid);
		});

		// Export as String
		exportItemS.setOnAction(e -> exportDialog(gridRepoString.export(grid)));

		// Load from compressed String
		loadItemSZ.setOnAction(e -> {
			Form form = new Form(stage, "Input compressed string");
			this.grid = gridRepoStringRLE.load(form.getText());
			updateGrid(grid);
		});

		// Export as compressed String
		exportItemSZ.setOnAction(e -> exportDialog(gridRepoStringRLE.export(grid)));

		// Exit
		exitItem.setOnAction(e -> System.exit(0));
	}

	private void updateGrid(Grid grid) {
		if (grid != null) {
			Pane gridView = new GridView(grid, pickerView);
			this.setCenter(gridView);
			stage.sizeToScene();
		}
	}

	private void exportDialog(String msg) {
		clipboardContent.putString(msg);
		clipboard.setContent(clipboardContent);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Export");
		alert.setHeaderText("Saved to clipboard");
		alert.getDialogPane().setContent(new TextArea(msg));
		alert.setResizable(true);
		alert.showAndWait();
	}

}
