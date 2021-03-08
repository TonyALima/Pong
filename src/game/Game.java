package game;

import players.Ball;
import players.Enemy;
import players.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable, KeyListener {

    private final Image image;
    // dimensions
    final int FACTOR = 160;
    final int WIDTH = 4 * FACTOR;
    final int HEIGHT = 3 * FACTOR;
    final int SCALE = 1;

    private boolean isRunning;
    private boolean pause;
    public boolean multiplayer;
    private final Color background;
    private final Canvas canvas;
    private final Thread renderThread;
    private static Player player1, player2;
    private static Enemy enemy;
    private static Ball ball;
    public static int scoreUp, scoreDown;

    // Constructor
    public Game( boolean multiplayer) {
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.canvas = new Canvas();
        this.canvas.addKeyListener(this);
        this.canvas.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.renderThread = new Thread(this);
        this.isRunning = true;
        this.multiplayer = multiplayer;

        Color color1 = new Color(36, 36, 220);
        player1 = new Player(240, HEIGHT - 48, color1);

        Color color2 = new Color(220, 36, 36);
        if (multiplayer) {
            player2 = new Player(240, 32, color2);
        } else {
            enemy = new Enemy(240, 32, color2);
        }
        ball = new Ball(multiplayer);

        background = new Color(50, 50, 50);
    }

    // Getters

    public Thread getRenderThread() {
        return renderThread;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static Enemy getEnemy() {
        return enemy;
    }

    public static Ball getBall() {
        return ball;
    }

    // Methods

    public static void restart(int HEIGHT, boolean multiplayer) {
        Color color1 = new Color(36, 36, 220);
        player1 = new Player(240, HEIGHT - 48, color1);

        Color color2 = new Color(220, 36, 36);
        if (multiplayer) {
            player2 = new Player(240, 32, color2);
        } else {
            enemy = new Enemy(240, 32, color2);
        }

        ball = new Ball(multiplayer);
    }

    public void tick() {
        player1.tick(WIDTH);
        if (multiplayer) {
            player2.tick(WIDTH);
        } else {
            enemy.tick();
        }
        ball.tick(WIDTH, HEIGHT);
    }

    public void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(background);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setFont(new Font("Times", Font.PLAIN, 50));
        g.setColor(new Color(0, 255, 0, 80));
        g.drawString("ARENA", 240, 250);

        player1.render(g);
        if (multiplayer) {
            player2.render(g);
        } else {
            enemy.render(g);
        }
        ball.render(g);

        g.setColor(new Color(200,200,200));
        g.setFont(new Font("Times", Font.PLAIN, 20));
        g.drawString("Pontos: " + scoreUp, 280, 26);
        g.drawString("Pontos: " + scoreDown, 280, HEIGHT - 6);

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player1.setRight(true);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player1.setLeft(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pause = !pause;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (multiplayer) {
                player2.setLeft(true);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            if (multiplayer) {
                player2.setRight(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player1.setRight(false);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player1.setLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (multiplayer) {
                player2.setLeft(false);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            if (multiplayer) {
                player2.setRight(false);
            }
        }
    }
}
