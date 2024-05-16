package game.template;

public class MainMenu extends MenuPage {
    public MainMenu(String name, PageManager pageManager) {
        super("main-menu", pageManager);

        addMenuItem("Start Game", () -> {
            System.out.println("Start Game");
            pageManager.setPage("game");
        });
        addMenuItem("Options", () -> {
            System.out.println("Options");
        });
    }
}
