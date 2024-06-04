package game.template;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;

import java.util.Comparator;

import org.checkerframework.checker.units.qual.A;

public class GameTile {
    private StackPane visualContent;
    private ArrayList<GameObject> layers = new ArrayList<GameObject>();
    private Game game;
    private int x;
    private int y;

    public GameTile(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;

        this.visualContent = new StackPane();
        this.visualContent.setPrefWidth(game.getTileSize());
        this.visualContent.setPrefHeight(game.getTileSize());
        this.visualContent.getStyleClass().add("game-tile");

        this.layers = new ArrayList<GameObject>();
    }

    public StackPane getVisualContent() {
        return visualContent;
    }

    public void setTile(GameObject tile) {
        layers.add(tile);
        layers.sort(Comparator.naturalOrder());
        updateVisualContent();
    }

    public void removeTile(GameObject tile) {
        layers.remove(tile);
        updateVisualContent();
    }

    public ArrayList<GameObject> getLayers() {
        return layers;
    }

    public void updateVisualContent() {
        if (layers.size() == 0) {
            visualContent.setStyle("-fx-background-color: transparent");
            return;
        }
        visualContent.setStyle("-fx-background-color: " + layers.get(layers.size() - 1).getColor());
    }
}
