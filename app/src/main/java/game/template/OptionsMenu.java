package game.template;

import javafx.scene.text.Text;

public class OptionsMenu extends MenuPage {
    public OptionsMenu(String name, PageManager pageManager) {
        super("options-menu", pageManager);

        addMenuItem("Fullscreen: " + App.app.getFullscreen(), () -> {
            System.out.println("Toggled fullscreen");
            App.app.setFullscreen(!App.app.getFullscreen());
        });
        addMenuItem("Back", () -> {
            System.out.println("Back");
            pageManager.back();
        });
    }

    @Override
    public String newText(Text text) {
        String content = text.getText();
        
        switch (content) {
            case "Fullscreen: true":
                return "Fullscreen: false";
            case "Fullscreen: false":
                return "Fullscreen: true";
            default:
                return content;
        }
    }
} 
