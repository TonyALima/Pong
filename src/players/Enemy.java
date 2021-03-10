package players;

import game.Game;

import java.awt.*;

public class Enemy extends Entity{

    private final double precision;

    // Constructor
    public Enemy(int x, int y, Color color, double precision){
        this.precision = precision;
        this.x = x;
        this.y = y;
        this.width = 160;
        this.height = 16;
        this.color = color;
    }

    // Methods
    public void tick(){
        this.x += (Game.getBall().x - this.x - 72 ) * precision;
    }
}
