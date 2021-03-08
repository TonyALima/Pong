package players;

import game.Game;

import java.awt.*;

public class Enemy extends Entity{

    // Constructor
    public Enemy(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.width = 160;
        this.height = 16;
        this.color = color;
    }

    // Methods
    public void tick(){
        this.x += (Game.getBall().x - this.x - 72);
    }
}
