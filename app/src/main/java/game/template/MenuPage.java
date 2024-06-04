package game.template;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuPage extends Page {
    public MenuPage(String name, PageManager pageManager) {
        super(name, pageManager);
    
        root.getStyleClass().add("menu");

        root.prefWidthProperty().bind(pageManager.main.widthProperty());
        root.prefHeightProperty().bind(pageManager.main.heightProperty());
        ((VBox) root).setAlignment(Pos.CENTER);
    }

    public void addMenuItem(String title, Runnable action) {
        StackPane button = new StackPane();
        Text text = new Text(title);
        button.getChildren().add(text);

        button.getStyleClass().add("menu-item");
        text.getStyleClass().add("text");

        button.setOnMouseClicked(event -> action.run());
        root.getChildren().add(button);

        button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, e -> {
            Text t = ((Text)button.getChildren().get(0));
            t.setText(newText(t));
        });
    }

    public String newText(Text text) {
        return text.getText();
    }
}
