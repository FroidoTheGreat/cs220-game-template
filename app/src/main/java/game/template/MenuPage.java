package game.template;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuPage extends Page {
    public MenuPage(PageManager pageManager) {
        super(new VBox(), "menu", pageManager);

        Text test = new Text("This is the menu page");
        ((VBox)root).getChildren().add(test);
    }
}
