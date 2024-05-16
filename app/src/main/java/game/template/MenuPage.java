package game.template;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuPage extends Page {
    public MenuPage(String name, PageManager pageManager) {
        super(name, pageManager);
    
        root.getStyleClass().add("menu");

        root.prefWidthProperty().bind(pageManager.main.widthProperty());
        root.prefHeightProperty().bind(pageManager.main.heightProperty());
        ((VBox) root).setAlignment(Pos.CENTER);
        ((VBox) root).setSpacing(20);
    }

    public void addMenuItem(String title, Runnable action) {
        Text text = new Text(title);
        text.getStyleClass().add("menu-item");
        text.setOnMouseClicked(event -> action.run());
        root.getChildren().add(text);
    }
}
