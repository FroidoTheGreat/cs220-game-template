package game.template;

import javafx.scene.layout.Pane;

public class Page {
    Pane root;
    String name;
    PageManager manager;

    public Page(Pane root, String name, PageManager manager) {
        this.root = root;
        this.name = name;
        this.manager = manager;
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
}
