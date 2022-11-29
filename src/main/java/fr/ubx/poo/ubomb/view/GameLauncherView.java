package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.editor.view.EditorView;
import fr.ubx.poo.ubomb.engine.GameEngine;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.launcher.GameLauncher;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class GameLauncherView extends BorderPane {
	private final FileChooser fileChooser = new FileChooser();

	public GameLauncherView(Stage stage) {
		// Create menu
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem loadItem = new MenuItem("Load from file ...");
		MenuItem defaultItem = new MenuItem("Load default configuration");
		MenuItem mapEditor = new MenuItem("Map Editor");
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
		menuFile.getItems().addAll(loadItem, defaultItem, new SeparatorMenuItem(), mapEditor, new SeparatorMenuItem(),
				exitItem);

		menuBar.getMenus().addAll(menuFile);
		this.setTop(menuBar);

		Text text = new Text("UBomb 2022");
		text.getStyleClass().add("message");
		VBox scene = new VBox();
		scene.getChildren().add(text);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		scene.getStyleClass().add("message");
		this.setCenter(scene);

		// Load from file
		loadItem.setOnAction(e -> {
			File file = fileChooser.showOpenDialog(stage);
			if (file != null) {
				Game game = GameLauncher.load(file.getPath());
				GameEngine engine = new GameEngine(game, stage);
				engine.start();
			}
		});

		defaultItem.setOnAction(e -> {
			Game game = GameLauncher.load();
			GameEngine engine = new GameEngine(game, stage);
			engine.start();
		});

		mapEditor.setOnAction(e -> {
			EditorView editorView = new EditorView(stage);
			Scene editorScene = new Scene(editorView);
			stage.setTitle("Map editor");
			stage.setScene(editorScene);
			stage.sizeToScene();
			stage.show();
		});

		// Exit
		exitItem.setOnAction(e -> System.exit(0));

	}

}
