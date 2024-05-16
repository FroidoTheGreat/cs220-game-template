package game.template;

import javafx.scene.layout.StackPane;

public class GameTile {
    private StackPane visualContent;

    public GameTile(Game game) {
        this.visualContent = new StackPane();
        this.visualContent.setPrefWidth(game.getTileSize());
        this.visualContent.setPrefHeight(game.getTileSize());
        this.visualContent.getStyleClass().add("game-tile");

        if (Math.random() < 0.5) {
            this.visualContent.getStyleClass().add("game-tile-black");
        } else {
            this.visualContent.getStyleClass().add("game-tile-white");
        }
    }

    public StackPane getVisualContent() {
        return visualContent;
    }
}
