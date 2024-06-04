package game.template;

import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class Game {
    public static final HashMap<TileName, String> colors = new HashMap<TileName, String>() {{
        put(TileName.EMPTY, "transparent");
        put(TileName.PLAYER, "orange");
        put(TileName.WALL, "black");
        put(TileName.B1, "cyan");
        put(TileName.FINISH, "rgb(25, 255, 110)");
        put(TileName.BOX, "brown");
        put(TileName.BUTTON, "rgb(205,205,205)");
    }};
    public static final String[] levels = new String[] {"intro", "wall", "buttons", "loop", "victory!"};

    private GridPane tileGrid;

    private int boardHeight = 10;
    private int boardWidth = 15;
    private int tileSize = 30;

    private int buttonsPressed;
    private int buttonsTotal;

    private int level = 0;

    private Player player;
    private Finish finish;

    private GamePage gamePage;

    private GameTile[][] tiles;

    public Game(GridPane tileGrid, GamePage gamePage) {
        this.tileGrid = tileGrid;
        this.gamePage = gamePage;

        tiles = new GameTile[boardWidth][boardHeight];

        generateEmptyGrid();

        level = 0;
        loadMap(levels[level]);

        updateLevelTitle();
    }

    public void updateLevelTitle() {
        gamePage.updateLevelTitle("level " + (this.level + 1) + ": " + levels[level]);
    }

    public void nextLevel() {
        level++;
        if (level >= levels.length) {
            level = 0;
        }
        loadMap(levels[level]);
        updateLevelTitle();
    }

    public void generateEmptyGrid() {
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                GameTile tile = new GameTile(this, x, y);
                tileGrid.add(tile.getVisualContent(), x, y);
                tiles[x][y] = tile;
            }
        }
    }

    public GameTile[][] getTiles() {
        return tiles;
    }

    public void resetGrid() {
        buttonsPressed = 0;
        buttonsTotal = 0;
        player = null;
        finish = null;
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                tiles[x][y].getLayers().clear();
                tiles[x][y].updateVisualContent();
            }
        }
    }
    public void loadMap(String mapName) {
        resetGrid();
        try {
            MapLoader mapLoader = new MapLoader(mapName);
            TileName[][][] mapTiles = mapLoader.tiles;
            int width = mapLoader.width;
            int height = mapLoader.height;
            int layers = mapLoader.layers;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    for (int l = 0; l < layers; l++) {
                        if (tiles.length > x && tiles[x].length > y) {
                            TileName type = mapTiles[l][x][y];
                            GameObject tile;
                            switch (type) {
                                case PLAYER:
                                    player = new Player(this, tiles, x, y);
                                    tile = player;
                                    break;
                                case BOX:
                                    tile = new Box(this, tiles, x, y);
                                    break;
                                case EMPTY:
                                    tile = null;
                                    break;
                                case FINISH:
                                    finish = new Finish(this, tiles, x, y);
                                    tile = finish;
                                    break;
                                case BUTTON:
                                    tile = new Button(this, tiles, x, y);
                                    buttonsTotal++;
                                    break;
                                default:
                                    tile = new GameObject(this, tiles, type, x, y, l, colors.get(type));
                                    break;
                            }
                            if (tile != null) {
                                tiles[x][y].setTile(tile);
                            }
                        }
                    }
                }
            }
            if (this.finish != null) {
                updateFinish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        level = 0;
        loadMap(levels[level]);
    }

    public int getBoardHeight() {
        return boardHeight;
    }
    public int getBoardWidth() {
        return boardWidth;
    }
    public int getTileSize() {
        return tileSize;
    }
    public void setBoardHeight(int boardWidth) {
        this.boardHeight = boardWidth;
    }
    public void setBoardWidth(int boardHeight) {
        this.boardWidth = boardHeight;
    }
    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public enum Dir {
        UP, DOWN, LEFT, RIGHT
    }

    public void handleMove(Dir d) {
        switch (d) {
            case UP:
                player.move(0, -1);
                break;
            case DOWN:
                player.move(0, 1);
                break;
            case LEFT:
                player.move(-1, 0);
                break;
            case RIGHT:
                player.move(1, 0);
                break;
        }
    }

    public void updateFinish() {
        if (buttonsPressed >= buttonsTotal) {
            finish.open();
        } else {
            finish.close();
        }
    }

    public int getLevel() {
        return level;
    }
    public int getButtonsTotal() {
        return buttonsTotal;
    }
    public void incrementButtonsPressed() {
        buttonsPressed++;
    }
    public void decrementButtonsPressed() {
        buttonsPressed--;
    }
    public int getButtonsPressed() {
        return buttonsPressed;
    }
    public Player getPlayer() {
        return player;
    }
    public Finish getFinish() {
        return finish;
    }
}