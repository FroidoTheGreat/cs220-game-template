package game.template;
import java.util.ArrayList;

public class GameObject implements Comparable<GameObject> {
    private TileName name;
    private int x;
    private int y;
    private int z;
    private String color; // FIXME: this will probably be replaced with a sprite
    private GameTile[][] context;
    private CollisionType collisionType;
    private Game game;

    public GameObject(Game game, GameTile[][] context, TileName name, int x, int y, int z, String color) {
        this.game = game;
        this.context = context;
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
        this.z = z;

        this.collisionType = CollisionType.SOLID;
    }

    public void setColor(String color) {
        this.color = color;
        this.context[this.x][this.y].updateVisualContent();
    }
    
    public GameTile[][] getContext() {
        return context;
    }

    public int compareTo(GameObject other) {
        return this.z - other.z;
    }

    public void setPosition(int x, int y) {
        this.context[this.x][this.y].removeTile(this);
        this.context[x][y].setTile(this);

        if (this.x != x || this.y != y) {
            for (GameObject object : this.context[this.x][this.y].getLayers()) {
                object.unUpon(this);
            }
            for (GameObject object : this.context[x][y].getLayers()) {
                object.upon(this);
            }
        }

        this.x = x;
        this.y = y;
    }
    public void move(int x, int y) {
        // FIXME: this is gonna need to collide and stuff, ya know?
        
        ArrayList<GameObject> destination = this.context[this.x + x][this.y + y].getLayers();

        ArrayList<GameObject> pushList = new ArrayList<GameObject>();
        for (GameObject object : destination) {
            if (
                object.getCollisionType() != CollisionType.PASSABLE &&
                (
                    object.getCollisionType() != CollisionType.PUSHABLE ||
                    !object.canPush(x, y)
                )
            
            ) {
                return;
            }
            if (object.getCollisionType() == CollisionType.PUSHABLE) {
                pushList.add(object);
            }
        }
        for (GameObject object : pushList) {
            object.push(x, y);
        }

        setPosition(this.x + x, this.y + y);
    }
    public Boolean canPush(int x, int y) {
        if (collisionType != CollisionType.PUSHABLE) {
            return false;
        }
        ArrayList<GameObject> layers = context[this.x + x][this.y + y].getLayers();
        for (GameObject layer : layers) {
            if (layer.getCollisionType() != CollisionType.PASSABLE) {
                return false;
            }
        }
        return true;
    }
    public void push(int x, int y) {
        if (collisionType == CollisionType.PUSHABLE) {
            setPosition(this.x + x, this.y + y);
            System.out.println("Pushed to " + this.x + ", " + this.y);
        }
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }
    public void setCollisionType(CollisionType collisionType) {
        this.collisionType = collisionType;
    }

    public void upon(GameObject object) {

    }
    public void unUpon(GameObject object) {

    }
    public Game getGame() {
        return game;
    }
    public TileName getName() {
        return name;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getColor() {
        return color;
    }
    public void setName(TileName name) {
        this.name = name;
    }
}
