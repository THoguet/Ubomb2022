package fr.ubx.poo.ubomb.editor.view;

import fr.ubx.poo.ubomb.launcher.Entity;
import fr.ubx.poo.ubomb.view.ImageResource;
import javafx.geometry.Insets;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PickerView extends GridPane {
    private final ToggleGroup group = new ToggleGroup();

    public PickerView() {
        this.setPadding(new Insets(15));
        int rows = 0, cols = 0;
        for (Entity e : Entity.values()) {
            ToggleButton btn = new ToggleButton();
            btn.setToggleGroup(group);
            btn.setUserData(e);
            btn.setGraphic(new ImageView(ImageResource.get(e)));
            this.add(btn, cols++ % 2, rows++ / 2);
        }
    }

    public Entity getSelected() {
        Toggle toggle = group.getSelectedToggle();
        if (toggle == null)
            return null;
        return (Entity) toggle.getUserData();
    }
}
