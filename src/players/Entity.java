package players;

import java.awt.*;

public abstract class Entity {

    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected Color color;

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
    }
}
