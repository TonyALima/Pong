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

    private boolean isRunning, pause;
    private Color background;
    private final Canvas canvas;
    private final Thread renderThread;
    private Player player1, player2;
    private Enemy enemy;
    private Ball ball;


    // Constructor
    public Game(boolean light) {
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.canvas = new Canvas();
        this.canvas.addKeyListener(this);
        this.canvas.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.renderThread = new Thread(this);
        this.isRunning = true;

        Color color1 = new Color(36, 36, 220);
        player1 = new Player(240, HEIGHT - 24, color1);

        Color color2 = new Color(220, 36, 36);
        enemy = new Enemy(240, 8, color2);

        ball = new Ball();

        if (light) {
            background = new Color(200, 200, 200);
        } else {
            background = new Color(50, 50, 50);
        }
    }

    // Getters

    public Thread getRenderThread() {
        return renderThread;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    // Methods

    public void tick() {
        player1.tick(WIDTH);
        enemy.tick();
        ball.tick();
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

        // Render graphics init

        g.setFont(new Font("Times", Font.PLAIN, 30));
        g.setColor(new Color(0, 255, 0,100));
        g.drawString("teste", 240, 250);

        // Render graphics end

        player1.render(g);
        enemy.render(g);
        ball.render(g);

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
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pause = !pause;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player1.setRight(false);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player1.setLeft(false);
        }
    }
}
