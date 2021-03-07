package game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Render implements Runnable {

    private final Image image;
    // dimensions
    final int FACTOR = 80;
    final int WIDTH = 4 * FACTOR;
    final int HEIGHT = 3 * FACTOR;
    final int SCALE = 2;

    private boolean isRunning;
    private final Canvas canvas;
    private final Thread renderThread;

    // Constructor
    public Render() {
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.renderThread = new Thread(this);
        this.isRunning = true;
    }

    // Getters

    public Thread getRenderThread() {
        return renderThread;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    // Methods
    public void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Render graphics init

        g.setFont(new Font("Times", Font.PLAIN, 30));
        g.setColor(new Color(0,255,0));
        g.drawString("teste", 100,100);

        // Render graphics end

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
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime)/ ns;
            lastTime = now;
            if(delta >= 1){
                render();
                delta--;
            }
        }
    }
}
