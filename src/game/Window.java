package game;

import menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Window implements Runnable {

    private JFrame frame;

    private final Menu menuParts;
    private final Thread windowThread;
    private static boolean play, menu, select, add, remove;

    private final Game game;

    // Constructor
    public Window() {
        this.game = new Game();

        this.windowThread = new Thread(this);
        this.windowThread.start();

        this.menuParts = new Menu(windowThread);

        initFrame();
    }

    // Getters

    public static boolean isSelect() {
        return select;
    }

    public static boolean isRemove() {
        return remove;
    }

    // Setters

    private static void setAllFalse() {
        Window.play = false;
        Window.menu = false;
        Window.select = false;
        Window.add = false;
        Window.remove = false;
    }

    public static void setPlay() {
        setAllFalse();
        Window.play = true;
    }

    public static void setMenu() {
        setAllFalse();
        Window.menu = true;
    }

    public static void setSelect() {
        setAllFalse();
        Window.select = true;
    }

    public static void setAdd() {
        setAllFalse();
        Window.add = true;
    }

    public static void setRemove() {
        setAllFalse();
        Window.remove = true;
    }

    // Methods
    private void initFrame() {
        frame = new JFrame("PONG");
        frame.add(menuParts.getSELECT_PANEL());
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.windowThread) {
                try {
                    this.windowThread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Container currentIn = frame.getContentPane();
            frame.remove(currentIn.getComponent(0));

            if (play) {
                game.getRenderThread().start();
                frame.add(game.getCanvas());
            } else if (menu) {
                frame.add(menuParts.getMENU_PANEL());
            } else if (select) {
                frame.add(menuParts.getSELECT_PANEL());
            } else if (add) {
                frame.add(menuParts.getADD_PANEL());
            } else if (remove) {
                frame.add(menuParts.getREMOVE_PANEL());
            }
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.requestFocus();
        }
    }
}
