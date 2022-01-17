package game;

import menu.Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Toolkit;
import java.util.Hashtable;

public class Window implements Runnable {

    private JFrame frame;

    private Menu menuParts;
    private Hashtable<String, JPanel> panels;
    private Thread windowThread;
    private static boolean play, menu, gameMode, options, keyMap;

    // Constructor
    public Window() {
        this.windowThread = new Thread(this);
        this.windowThread.start();

        this.menuParts = new Menu(windowThread);
        this.panels = this.menuParts.getPanels();

        initFrame();
    }

    // Setters

    private static void setAllFalse() {
        Window.play = false;
        Window.menu = false;
        Window.gameMode = false;
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
        Window.gameMode = true;
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
        frame.setPreferredSize(new DimensionUIResource(300, 550));
        frame.add(panels.get("menu"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/Pong_Icon.png"));
        frame.pack();
        frame.setLocationRelativeTo(null);
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
            JPanel[] panelsArray = panels.values().toArray(new JPanel[panels.size()]);
            for (int i = 0; i < panels.size(); i++){
                try {
                    frame.remove(panelsArray[i]);
                }catch (Exception e) {}
            }

            if (play) {
                Game game = new Game(menuParts.isMultiplayer(), menuParts.getDifficultyLevel());
                game.getGameThread().start();
                frame.add(game.getCanvas());
                frame.setPreferredSize(new DimensionUIResource(640, 480));
                game.getCanvas().requestFocus();
                frame.setResizable(false);
            } else if (menu) {
                frame.add(panels.get("menu"));
                frame.setPreferredSize(new DimensionUIResource(300, 550));
            } else if (options) {
                frame.add(panels.get("options"));
                frame.setPreferredSize(new DimensionUIResource(300, 450));
            }else if (gameMode){
                frame.add(panels.get("gameMode"));
                frame.setPreferredSize(new DimensionUIResource(300, 200));
            }else if (keyMap){
                frame.add(panels.get("controls"));
                frame.setPreferredSize(new DimensionUIResource(300, 500));
            }
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }
}
