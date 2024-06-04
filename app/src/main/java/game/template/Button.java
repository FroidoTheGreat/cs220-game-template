package game.template;

public class Button extends GameObject {
    public Button(Game game, GameTile[][] context, int x, int y) {
        super(game, context, TileName.BUTTON, x, y, 1, Game.colors.get(TileName.BUTTON));
        this.setCollisionType(CollisionType.PASSABLE);
    }

    public void upon(GameObject object) {
        super.upon(object);
        Game game = this.getGame();
        game.incrementButtonsPressed();
        game.updateFinish();
    }
    public void unUpon(GameObject object) {
        super.unUpon(object);
        Game game = this.getGame();
        game.decrementButtonsPressed();
        game.updateFinish();
    }
}
