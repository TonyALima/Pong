package players;

import game.Game;

import java.awt.*;
import java.util.Random;

public class Ball extends Entity {


    private double dx, dy;
    private final double speed = 3;
    private final boolean multiplayer;

    // Constructor
    public Ball(boolean multiplayer) {
        this.multiplayer = multiplayer;
        this.x = 312;
        this.y = 232;
        this.width = 16;
        this.height = 16;
        this.color = new Color(200, 200, 0);

        double fracAngle;
        boolean choice = new Random().nextBoolean();
        if (choice) {
            do {
                fracAngle = Math.random();
            } while (fracAngle < (1f / 4) || fracAngle > (3f / 4));
        } else {
            do {
                fracAngle = (Math.random() + 1);
            } while (fracAngle > (7f / 4) || fracAngle < (5f / 4));
        }

        double angle = fracAngle * Math.PI;
        this.dx = Math.cos(angle);
        this.dy = Math.sin(angle);
    }

    // Methods
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
        Random random = new Random();
        if (bound.intersects(playerBound)) {
            double fracAngle;
            do {
                fracAngle = (random.nextDouble() + 1);
            } while (fracAngle > (7f / 4) || fracAngle < (5f / 4));
            double angle = fracAngle * Math.PI;
            this.dy = Math.sin(angle);
        } else if (bound.intersects(enemyBound)) {
            double fracAngle;
            do {
                fracAngle = random.nextDouble();
            } while (fracAngle < (1f / 4) || fracAngle > (3f / 4));
            double angle = fracAngle * Math.PI;
            this.dy = Math.sin(angle);
        }

        if (this.x <= 0 || this.x >= (limitX - width)) {
            this.dx *= -1;
        }

        if (this.y < -height) {
            System.out.println("ponto do jogador");
            Game.restart(limitY, multiplayer);
        } else if (this.y > limitY) {
            System.out.println("ponto do inimigo");
            Game.restart(limitY, multiplayer);
        }
    }
}
