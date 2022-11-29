package fr.ubx.poo.ubomb.editor.model;

import java.util.ArrayList;
import java.util.List;

import fr.ubx.poo.ubomb.launcher.Entity;
import fr.ubx.poo.ubomb.view.ImageResource;
import fr.ubx.poo.ubomb.editor.view.PickerView;
import fr.ubx.poo.ubomb.editor.view.Tile;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.BorderPane;

public class GridView extends BorderPane {
    private final Grid grid;
    private final PickerView pickerView;

    public GridView(Grid grid, PickerView pickerView) {
        this.grid = grid;
        this.pickerView = pickerView;
        setPrefSize(grid.getWidth() * ImageResource.SIZE,
                grid.getHeight() * ImageResource.SIZE);
        for (int i = 0; i < this.grid.getWidth(); i++) {
            for (int j = 0; j < this.grid.getHeight(); j++) {
                createTile(i, j);
            }
        }
    }

    private void createTile(int i, int j) {
        int layoutX = i * ImageResource.SIZE;
        int layoutY = j * ImageResource.SIZE;
        Tile tile = new Tile(ImageResource.get(this.grid.get(i, j)), layoutX,
                layoutY);
        getChildren().add(tile);
        tile.setOnMouseClicked(e -> update(tile, i, j));
        tile.setOnMouseEntered(e -> {
            if (e.isShiftDown()) {
                update(tile, i, j);
            }
        });
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.2);
        tile.setOnMouseEntered(e -> {
            if (e.isShiftDown()) {
                update(tile, i, j);
            }
            tile.setEffect(colorAdjust);
        });
        tile.setOnMouseExited(e -> {
            tile.setEffect(null);
        });
    }

    private void update(Tile tile, int i, int j) {
        if (pickerView.getSelected() != null && pickerView.getSelected() != grid.get(i, j)) {
            getChildren().remove(tile);
            grid.set(i, j, pickerView.getSelected());
            createTile(i, j);
        }
    }
}
