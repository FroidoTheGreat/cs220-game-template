package game.template;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GamePage extends Page {
    private Game game;
    private GridPane grid;
    private BorderPane topBar;
    private BorderPane bottomBar;
    private StackPane exitButton;
    private StackPane menuButton;

    public GamePage(String name, PageManager pageManager) {
        super("game", pageManager, new BorderPane());

        // game area
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        ((BorderPane) root).setCenter(grid);

        this.game = new Game(grid);

        // top and bottom bars
        topBar = new BorderPane();
        topBar.setPrefHeight(100);
        topBar.getStyleClass().add("top-bar");
        ((BorderPane) root).setTop(topBar);
        createTopBar();
        

        bottomBar = new BorderPane();
        bottomBar.prefHeightProperty().bind(topBar.heightProperty());
        bottomBar.getStyleClass().add("bottom-bar");
        ((BorderPane) root).setBottom(bottomBar);
        
        root.getStyleClass().add("game-page");
    }

    public void createTopBar() {
        exitButton = new StackPane();
        exitButton.getStyleClass().add("exit-button");
        exitButton.setOnMouseClicked(event -> {
            manager.setPage("main-menu");
        });
        exitButton.setPrefHeight(100);
        exitButton.setPrefWidth(100);
        topBar.setRight(exitButton);
    }
}
