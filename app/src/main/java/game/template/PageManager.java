package game.template;

import java.util.HashMap;
import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class PageManager {
    HashMap<String, Page> pages = new HashMap<String, Page>();
    Pane main;
    Page current;
    ArrayList<Page> history = new ArrayList<Page>();

    public PageManager(Pane main, Page initial) {
        this.main = main;
        this.current = initial;

        pages = new HashMap<String, Page>();
        if (initial != null) {
            addPage(initial);
        } else {
            System.out.println("Warning: initial page is null");
        }
    }

    public PageManager(Pane main) {
        this(main, null);
    }

    public void addPage(Page page) {
        pages.put(page.getName(), page);

        // if there is no current page, set the current page to the first page added
        if (current == null) {
            setPage(page.name);
        }
    }

    public Page getPage(String name) {
        if (pages.get(name) == null) {
            throw new IllegalArgumentException("Scene " + name + " does not exist");
        }
        return pages.get(name);
    }

    public void removePage(String name) {
        pages.remove(name);
    }

    public void setPage(String name) {
        if (pages.get(name) == null) {
            throw new IllegalArgumentException("Scene " + name + " does not exist");
        }
        history.add(current);
        current = pages.get(name);
        main.getChildren().clear();
        main.getChildren().add(current.getRoot());
    }
    public void back() {
        if (history.size() > 0) {
            current = history.remove(history.size() - 1);
            main.getChildren().clear();
            main.getChildren().add(current.getRoot());
        }
    }

    public Page getCurrent() {
        return current;
    }
}
