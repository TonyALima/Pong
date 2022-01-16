package players;

import game.Game;

import java.awt.*;
import java.util.Random;

public class Ball extends Entity {


    private double dx, dy;
    private final boolean multiplayer;
    private double speed;
    private double enemyPrecision;
    private Random generator = new Random();


    // Constructor
    public Ball(boolean multiplayer, double speed, double enemyPrecision) {
        this.speed = speed;
        this.enemyPrecision = enemyPrecision;
        this.multiplayer = multiplayer;
        this.x = 312;
        this.y = 232;
        this.width = 16;
        this.height = 16;
        this.color = new Color(200, 200, 0);

        int angle = getAngle(new Random().nextBoolean());
        this.dx = Math.cos(Math.toRadians(angle));
        this.dy = Math.sin(Math.toRadians(angle));
    }

    // Methods

    private int getAngle(boolean isDown) {
        int angle;
        int variation = generator.nextInt(120);
        if (isDown) {
            angle = variation + 210;
        } else {
            angle = variation + 30;
        }
        return angle;
    }

    public void tick(int limitX, int limitY) {
        this.x += dx * speed;
        this.y += dy * speed;

        Rectangle bound = new Rectangle((int) x, (int) y, width, height);
        Rectangle playerBound = new Rectangle((int) Game.getPlayer1().x, (int) Game.getPlayer1().y,
                Game.getPlayer1().width, Game.getPlayer1().height);
        Rectangle enemyBound;
        if (!multiplayer) {
            enemyBound = new Rectangle((int) Game.getEnemy().x, (int) Game.getEnemy().y,
                    Game.getEnemy().width, Game.getEnemy().height);
        } else {
            enemyBound = new Rectangle((int) Game.getPlayer2().x, (int) Game.getPlayer2().y,
                    Game.getPlayer2().width, Game.getPlayer2().height);
        }

        int angle;
        if (bound.intersects(enemyBound)) {
            angle = getAngle(false);
            this.dy = Math.sin(Math.toRadians(angle));
            this.dx = Math.cos(Math.toRadians(angle));
        } else if (bound.intersects(playerBound)) {
            angle = getAngle(true);
            this.dy = Math.sin(Math.toRadians(angle));
            this.dx = Math.cos(Math.toRadians(angle));
        }

        if (this.x <= 0 || this.x >= (limitX - width)) {
            this.dx *= -1;
        }

        if (this.y < -height) {
            Game.scoreDown++;
            Game.restart(limitY, multiplayer, speed, enemyPrecision);
        } else if (this.y > limitY) {
            Game.scoreUp++;
            Game.restart(limitY, multiplayer, speed, enemyPrecision);
        }
    }
}
