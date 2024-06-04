package game.template;

public class Box extends GameObject{
    public Box(Game game, GameTile[][] context, int x, int y) {
        super(game, context, TileName.B1, x, y, 1, Game.colors.get(TileName.BOX));
        this.setCollisionType(CollisionType.PUSHABLE);
    }
}
