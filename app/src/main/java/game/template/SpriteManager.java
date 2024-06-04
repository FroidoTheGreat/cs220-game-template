package game.template;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;
import java.io.FileInputStream;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SpriteManager {
    private static Sprite root;

    public static Sprite loadSprites(String pathToRoot) {
        root = new Sprite("root", pathToRoot, null);
        root.loadChildren();
        System.out.println(root.toString());

        System.out.println(getSprite("test/test"));

        return root;
    }

    public static Sprite getSprite(String spritePath) {
        String[] path = spritePath.split("/");
        Sprite current = root;
        for (int i = 0; i < path.length; i++) {
            if (current.children.get(path[i]) == null) { 
                throw new IllegalArgumentException("Sprite " + spritePath + " does not exist");
            }
            current = current.children.get(path[i]);
        }
        return current;
    }

    public static Image getImage(String spritePath) {
        return getSprite(spritePath).getImage();
    }

    private static class Sprite {
        private String name;
        private HashMap<String, Sprite> children;
        private String path;
        private Image image;

        public Sprite(String name, String path, Image image) {
            this.name = name;
            this.path = path;
            this.image = image;

            children = new HashMap<String, Sprite>();
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Image getImage() {
            if (image == null) {
                throw new IllegalArgumentException("This is a directory sprite without an image");
            }
            return image;
        }

        public void loadChildren() {
            // load children from path
            HashSet<Path> childPaths = new HashSet<Path>();

            // find all the child paths
            try (Stream<Path> stream = Files.list(Paths.get(path))) {
                stream.forEach(path -> {
                    childPaths.add(path);
                });
            } catch (IOException e) {
                System.out.println("Could not load children of sprite: " + name);
                return;
            }

            // loop through the paths and create sprites
            for (Path childPath : childPaths) {
                // create a new sprite and add it to the children
                String childName = childPath.getFileName().toString();

                // remove file extension from name
                int dotIndex = childName.lastIndexOf(".");
                if (dotIndex == -1) {
                    dotIndex = childName.length();
                }
                childName = childName.substring(0, dotIndex);

                Sprite child = new Sprite(childName, childPath.toString(), null);
                children.put(childName.toString(), child);
                // when the sprite is a directory, load its children
                if (Files.isDirectory(childPath)) {
                    child.loadChildren();
                // when the sprite is a file, load the image
                } else {
                    // get the file extension
                    String extension = childPath.getFileName().toString().substring(childPath.getFileName().toString().lastIndexOf(".") + 1);
                    if (extension.equals("png")) {
                        // load the image
                        try {
                            child.setImage(new Image(new FileInputStream(childPath.toString())));
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Could not load image: " + childPath.toString());
                        }
                    }
                }
            }
        }

        @Override
        public String toString() {
            if (image != null) {
                return name;
            }

            String toString = "{" + name + ": ";

            ArrayList<String> keys = new ArrayList<String>(children.keySet());

            for (int i = 0; i < keys.size(); i++) {
                String childName = keys.get(i);
                Sprite child = children.get(childName);

                toString += child.toString();

                if (i < keys.size() - 1) {
                    toString += ", ";
                }
            }

            return toString + "}";
        }
    }
}
