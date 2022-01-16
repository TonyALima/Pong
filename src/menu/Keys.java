package menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Keys {

    private Image keys;

    // Constructor
    public Keys() {
        try {
            keys = ImageIO.read(getClass().getResource("../res/keys.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters

    public Image getKeys() {
        return keys;
    }
}
