package game.template;

public class Finish extends GameObject{
    public Finish(Game game, GameTile[][] context, int x, int y) {
        super(game, context, TileName.FINISH, x, y, -1, Game.colors.get(TileName.FINISH));
        this.setCollisionType(CollisionType.PASSABLE);
    }
    
    public void open () {
        this.setCollisionType(CollisionType.PASSABLE);
        this.setColor(this.getGame().colors.get(TileName.FINISH));
    }
    public void close () {
        this.setCollisionType(CollisionType.SOLID);
        this.setColor("rgb(59, 117, 96)");
    }
}
