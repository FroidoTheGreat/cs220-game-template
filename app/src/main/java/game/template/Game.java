package game.template;

import javafx.scene.layout.GridPane;

public class Game {
    private GridPane tileGrid;

    private int boardSize = 15;
    private int tileSize = 30;

    public Game(GridPane tileGrid) {
        this.tileGrid = tileGrid;

        generateEmptyGrid();
    }

    public void generateEmptyGrid() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                GameTile tile = new GameTile(this);
                tileGrid.add(tile.getVisualContent(), i, j);
            }
        }
    }

    public void start() {
        
    }

    public int getBoardSize() {
        return boardSize;
    }
    public int getTileSize() {
        return tileSize;
    }
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
}
