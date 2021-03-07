package game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Render implements Runnable {

    private final Image image;
    // Image dimensions
    private final int WIDTH;
    private final int HEIGHT;
    private final int SCALE;

    private boolean isRunning;
    private final Canvas canvas;
    private final Thread renderThread;

    // Constructor
    public Render( int SCALE, int WIDTH, int HEIGHT, Canvas canvas) {
        this.SCALE = SCALE;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.canvas = canvas;
        this.renderThread = new Thread(this);
        this.isRunning = true;
    }

    // Getters

    public Thread getRenderThread() {
        return renderThread;
    }

    // Methods

    public void render(Canvas canvas) {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Render graphics init

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
                render(canvas);
                delta--;
            }
        }
        //game.stop();
    }
}
