package players;

import java.awt.*;

public class Player extends Entity{

    private boolean right, left;

    // Constructor
    public Player(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.width = 160;
        this.height = 16;
        this.color = color;
    }

    // Setters

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    // Methods
    public void tick(int limit){
        if (right){
            x+=4;
        }else if(left){
            x-=4;
        }
        if (x < 0){
            x = 0;
        }else if (x > (limit - width)){
            x = limit - width;
        }
    }
}
