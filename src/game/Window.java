package game;

import menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Window implements Runnable {

    private JFrame frame;

    private final Menu menuParts;
    private final Thread windowThread;
    private static boolean play, menu, selectMultiplayer, options, keyMap;

    // Constructor
    public Window() {
        this.windowThread = new Thread(this);
        this.windowThread.start();

        this.menuParts = new Menu(windowThread);

        initFrame();
    }

    // Setters

    private static void setAllFalse() {
        Window.play = false;
        Window.menu = false;
        Window.selectMultiplayer = false;
        Window.options = false;
        Window.keyMap = false;
    }

    public static void setPlay() {
        setAllFalse();
        Window.play = true;
    }

    public static void setMenu() {
        setAllFalse();
        Window.menu = true;
    }

    public static void setMultiplayer() {
        setAllFalse();
        Window.selectMultiplayer = true;
    }

    public static void setOptions() {
        setAllFalse();
        Window.options = true;
    }

    public static void setKeyMap(){
        setAllFalse();
        Window.keyMap = true;
    }

    // Methods
    private void initFrame() {
        frame = new JFrame("PONG");
        frame.add(menuParts.getMULTIPLAYER_PANEL());
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
                Game game = new Game(menuParts.isMultiplayer(), menuParts.getDifficultyLevel());
                game.getGameThread().start();
                frame.add(game.getCanvas());
                game.getCanvas().requestFocus();
            } else if (menu) {
                frame.add(menuParts.getMENU_PANEL());
            } else if (options) {
                frame.add(menuParts.getOPTIONS_PANEL());
            }else if (selectMultiplayer){
                frame.add(menuParts.getMULTIPLAYER_PANEL());
            }else if (keyMap){
                frame.add(menuParts.getCONTROLS_PANEL());
            }
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }
}
