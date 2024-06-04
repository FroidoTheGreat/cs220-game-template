package game.template;

public class PauseMenu extends MenuPage {
    public PauseMenu(String name, PageManager pageManager) {
        super("pause-menu", pageManager);

        addMenuItem("Resume", () -> {
            System.out.println("Resume");
            pageManager.setPage("game");
        });
        addMenuItem("Reset Level", () -> {
            System.out.println("Reset Level");
            Game game = ((GamePage) pageManager.getPage("game")).getGame();
            game.resetGrid();
            game.loadMap(Game.levels[game.getLevel()]);
            pageManager.setPage("game");
        });
        addMenuItem("Options", () -> {
            System.out.println("Options");
            pageManager.setPage("options-menu");
        });
        addMenuItem("Main Menu", () -> {
            System.out.println("Main Menu");
            pageManager.setPage("main-menu");
        });
    }
}
