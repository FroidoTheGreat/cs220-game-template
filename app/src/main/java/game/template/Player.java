package game.template;

public class Player extends GameObject {
    public Player(Game game, GameTile[][] context, int x, int y) {
        super(game, context, TileName.PLAYER, x, y, 1, Game.colors.get(TileName.PLAYER));
    }

    public void move(int x, int y) {
        super.move(x, y);
        Boolean isOnFinish = false;
        for (GameObject object : this.getContext()[this.getX()][this.getY()].getLayers()) {
            if (object.getName() == TileName.FINISH) {
                isOnFinish = true;
            }
        }
        if (isOnFinish) {
            this.getGame().nextLevel();
        }
    }
}
