package menu;

import game.Window;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.util.Hashtable;

public class Menu {

    private GamePanel menuPanel;
    private GamePanel optionsPanel;
    private GamePanel gameModePanel;
    private GamePanel controlsPanel;
    private Thread windowThread;
    private boolean multiplayer;
    private int difficultyLevel;

    // Constructor
    public Menu(Thread windowThread) {
        startComponents();
        this.windowThread = windowThread;
    }

    // Getters
    public boolean isMultiplayer() {
        return multiplayer;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public Hashtable<String, JPanel> getPanels() {
        Hashtable<String, JPanel> panels = new Hashtable<String, JPanel>();
        panels.put("menu", menuPanel);
        panels.put("options", optionsPanel);
        panels.put("gameMode", gameModePanel);
        panels.put("controls", controlsPanel);
        return panels;
    } 

    // Methods
    private void startComponents() {
        Font FONT = new Font("Times", Font.BOLD, 15);
        
        JLabel selectLabel = new JLabel("Dificuldade");
        JLabel nameLabel = new JLabel("Bom dia");
        JLabel keyInstructLabel = new JLabel("CONTROLES ");
        JLabel pauseLabel = new JLabel("Pause   :   ESC");
        JLabel keyMapLabel = new JLabel();
        JComboBox<String> difficulty = new JComboBox<>();
        Keys keys = new Keys();
        
        pauseLabel.setFont(new Font("Times", Font.BOLD, 17));
        pauseLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(FONT);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        selectLabel.setFont(FONT);
        selectLabel.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon icon = new ImageIcon(keys.getKeys());
        keyMapLabel.setIcon(icon);
        keyMapLabel.setHorizontalAlignment(JLabel.CENTER);
        keyInstructLabel.setHorizontalAlignment(JLabel.CENTER);
        
        GameButton multiplayerButton = new GameButton("Multiplayer");
        multiplayerButton.addActionListener(e -> {
            this.multiplayer = true;
            Window.setKeyMap();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });

        GameButton singlePlayerButton = new GameButton("Singleplayer");
        singlePlayerButton.addActionListener(e -> {
            this.multiplayer = false;
            Window.setKeyMap();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to key map panel

        GameButton selectMultiplayerButton = new GameButton("Game mode");
        selectMultiplayerButton.addActionListener(e -> {
            Window.setMultiplayer();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to selectPanel

        GameButton playButton = new GameButton("PLAY");
        playButton.addActionListener(e -> {
            Window.setPlay();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to the game

        GameButton optionsButton = new GameButton("Options");
        optionsButton.addActionListener(e -> {
            Window.setOptions();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to options panel

        GameButton okButton1 = new GameButton("OK");
        okButton1.addActionListener(e -> {
            Window.setMenu();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });

        GameButton okButton2 = new GameButton("OK");
        okButton2.addActionListener(e -> {
            Window.setMenu();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });

        difficulty.setFont(FONT);
        difficulty.setModel(new DefaultComboBoxModel<>(new String[]{"Easy", "Medium", "Hard", "God"}));
        difficulty.addActionListener(e ->
            difficultyLevel = difficulty.getSelectedIndex()
        );

        menuPanel = new GamePanel(new JComponent[]{nameLabel, selectMultiplayerButton, optionsButton, playButton});

        optionsPanel = new GamePanel(new JComponent[]{selectLabel, difficulty,  okButton1});

        gameModePanel = new GamePanel(new JComponent[]{multiplayerButton, singlePlayerButton});

        controlsPanel = new GamePanel(new JComponent[]{keyInstructLabel, keyMapLabel, pauseLabel, okButton2});
    }
}