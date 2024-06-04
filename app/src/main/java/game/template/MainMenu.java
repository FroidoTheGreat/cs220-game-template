package game.template;

public class MainMenu extends MenuPage {
    public MainMenu(String name, PageManager pageManager) {
        super("main-menu", pageManager);

        addMenuItem("Play", () -> {
            System.out.println("Start Game");
            pageManager.setPage("game");
            ((GamePage)pageManager.getPage("game")).getGame().start();
        });
        addMenuItem("Options", () -> {
            System.out.println("Options");
            pageManager.setPage("options-menu");
        });
        addMenuItem("Exit", () -> {
            System.out.println("Exit");
            System.exit(0);
        });
    }
}
