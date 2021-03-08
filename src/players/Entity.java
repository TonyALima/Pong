package players;

import java.awt.*;

public abstract class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Color color;

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }
}
