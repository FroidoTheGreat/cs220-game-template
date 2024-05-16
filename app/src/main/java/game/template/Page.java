package game.template;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Page {
    Pane root;
    String name;
    PageManager manager;

    public Page(String name, PageManager manager, Pane root) {
        this.root = root;
        this.name = name;
        this.manager = manager;

        this.root.getStyleClass().add("page");
        this.root.prefWidthProperty().bind(manager.main.widthProperty());
        this.root.prefHeightProperty().bind(manager.main.heightProperty());
    }

    public Page(String name, PageManager manager) {
        this(name, manager, new VBox());
    }

    public Pane getRoot() {
        return root;
    }

    public String getName() {
        return name;
    }

    public void activate() {
        manager.setPage(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
