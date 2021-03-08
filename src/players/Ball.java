package players;

import java.awt.*;

public class Ball extends Entity {

    private double dx, dy;
    private final double speed = 1;

    // Constructor
    public Ball() {
        this.x = 312;
        this.y = 232;
        this.width = 16;
        this.height = 16;
        this.color = new Color(200, 200, 0);


    }

    // Methods
    public void tick() {
        this.x += 1;
        this.y += 2;
    }
}
