package game.template;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class GamePage extends Page {
    private Game game;
    private GridPane grid;
    private BorderPane topBar;
    private BorderPane bottomBar;
    private StackPane exitButton;
    private StackPane restartButton;
    private GridPane directionalPad;
    private Text levelTitle;

    public GamePage(String name, PageManager pageManager) {
        super("game", pageManager, new BorderPane());

        // game area
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        ((BorderPane) root).setCenter(grid);
        grid.getStyleClass().add("game-grid");
        grid.getStyleClass().add("TEST-game-grid");

        // top and bottom bars
        topBar = new BorderPane();
        topBar.setPrefHeight(100);
        topBar.getStyleClass().add("top-bar");
        topBar.getStyleClass().add("TEST-top-bar");
        ((BorderPane) root).setTop(topBar);
        createTopBar();
        
        bottomBar = new BorderPane();
        bottomBar.prefHeightProperty().bind(topBar.heightProperty());
        bottomBar.getStyleClass().add("bottom-bar");
        bottomBar.getStyleClass().add("TEST-bottom-bar");
        ((BorderPane) root).setBottom(bottomBar);
        createBottomBar();
        
        root.getStyleClass().add("game-page");

        App.app.getScene().addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, e -> {
            System.out.println(e.getCode());
            switch (e.getCode()) {
                case W:
                    game.handleMove(Game.Dir.UP);
                    break;
                case S:
                    game.handleMove(Game.Dir.DOWN);
                    break;
                case A:
                    game.handleMove(Game.Dir.LEFT);
                    break;
                case D:
                    game.handleMove(Game.Dir.RIGHT);
                    break;
                case R:
                    game.resetGrid();
                    game.loadMap(Game.levels[game.getLevel()]);
                    break;
                case ESCAPE:
                    if (manager.getCurrent() == this) {
                        manager.setPage("pause-menu");
                    }
                    break;
                default:
                    break;
            }
        });

        this.game = new Game(grid, this);
    }

    public void updateLevelTitle(String title) {
        levelTitle.setText(title);
    }

    public void createTopBar() {
        exitButton = new StackPane();
        exitButton.getStyleClass().add("exit-button");
        exitButton.setOnMouseClicked(event -> {
            manager.setPage("pause-menu");
        });
        exitButton.setPrefHeight(100);
        exitButton.setPrefWidth(100);
        topBar.setRight(exitButton);

        Image buttonImage = SpriteManager.getImage("buttons/menu");
        ImageView buttonImageView = new ImageView(buttonImage);
        exitButton.getChildren().add(buttonImageView);
        

        restartButton = new StackPane();
        restartButton.getStyleClass().add("restart-button");
        restartButton.setOnMouseClicked(event -> {
            game.resetGrid();
            game.loadMap(Game.levels[game.getLevel()]);
        });
        restartButton.setPrefHeight(100);
        restartButton.setPrefWidth(100);
        topBar.setLeft(restartButton);  

        Image restartImage = SpriteManager.getImage("buttons/restart");
        ImageView restartImageView = new ImageView(restartImage);
        restartButton.getChildren().add(restartImageView);

        // level title
        levelTitle = new Text("Level 1");
        levelTitle.getStyleClass().add("text");
        topBar.setCenter(levelTitle);
    }

    public void createBottomBar() {
        directionalPad = new GridPane();
        directionalPad.getStyleClass().add("directional-pad");
        bottomBar.setCenter(directionalPad);
        directionalPad.setAlignment(Pos.CENTER);
        bottomBar.setStyle("-fx-padding: 20;");
        directionalPad.setHgap(6);
        directionalPad.setVgap(6);

        Image upArrow = SpriteManager.getImage("arrows/up");
        ImageView upArrowView = new ImageView(upArrow);
        directionalPad.add(upArrowView, 1, 0);
        upArrowView.setOnMousePressed(event -> {
            game.handleMove(Game.Dir.UP);
        });
        upArrowView.getStyleClass().add("arrow");
        

        Image downArrow = SpriteManager.getImage("arrows/down");
        ImageView downArrowView = new ImageView(downArrow);
        directionalPad.add(downArrowView, 1, 2);
        downArrowView.setOnMousePressed(event -> {
            game.handleMove(Game.Dir.DOWN);
        });
        downArrowView.getStyleClass().add("arrow");

        Image leftArrow = SpriteManager.getImage("arrows/left");
        ImageView leftArrowView = new ImageView(leftArrow);
        directionalPad.add(leftArrowView, 0, 1);
        leftArrowView.setOnMousePressed(event -> {
            game.handleMove(Game.Dir.LEFT);
        });
        leftArrowView.getStyleClass().add("arrow");

        Image rightArrow = SpriteManager.getImage("arrows/right");
        ImageView rightArrowView = new ImageView(rightArrow);
        directionalPad.add(rightArrowView, 2, 1);
        rightArrowView.setOnMousePressed(event -> {
            game.handleMove(Game.Dir.RIGHT);
        });
        rightArrowView.getStyleClass().add("arrow");
    }

    public Game getGame() {
        return game;
    }
}
