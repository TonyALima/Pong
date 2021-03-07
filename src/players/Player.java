package players;

import java.awt.*;

public class Player {

    private static int numberPlayer, record, currentScore;
    private static String name;
    private int x, y;
    private final Color color;
    private boolean right, left;

    // Constructor
    public Player(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Setters

    public static void setNumberPlayer(int numberPlayer) {
        Player.numberPlayer = numberPlayer;
    }

    public static void setRecord(int record) {
        Player.record = record;
    }

    public static void setCurrentScore(int currentScore) {
        Player.currentScore = currentScore;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    // Methods
    public void tick(int limit){
        if (right){
            x++;
        }else if(left){
            x--;
        }
        if (x < 0){
            x = 0;
        }else if (x > (limit - 80)){
            x = limit - 80;
        }
    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,80,8);
    }
}
