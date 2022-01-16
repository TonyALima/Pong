package menu;

import game.Window;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class Menu {

    private JPanel menu_panel;
    private JPanel optionsPanel;
    private JPanel multiplayerPanel;
    private JPanel controlsPanel;
    private Thread windowThread;
    private boolean multiplayer;
    private int difficultyLevel;

    // Constructor
    public Menu(Thread windowThread) {
        this.menu_panel = new JPanel();
        this.optionsPanel = new JPanel();
        this.multiplayerPanel = new JPanel();
        this.controlsPanel = new JPanel();
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
        panels.put("menu", menu_panel);
        panels.put("options", optionsPanel);
        panels.put("multiplayer", multiplayerPanel);
        panels.put("controls", controlsPanel);
        return panels;
    } 

    // Methods
    private void startComponents() {
        Color BACK_BUTTONS_COLOR = new Color(100, 100, 100);
        Color BACK_PANELS_COLOR = new Color(200, 200, 200);
        Color FONT_COLOR = new Color(220, 220, 220);
        Font FONT = new Font("Times", Font.BOLD, 15);
        
        JLabel selectLabel = new JLabel("Dificuldade");
        JLabel nameLabel = new JLabel("Bom dia");
        JLabel keyInstructLabel = new JLabel("CONTROLES ");
        JLabel pauseLabel = new JLabel("Pause   :   ESC");
        JLabel keyMapLabel = new JLabel();
        JComboBox<String> difficulty = new JComboBox<>();
        Keys keys = new Keys();
        JButton multiplayerButton = new JButton("Multiplayer");
        JButton singlePlayerButton = new JButton("Single player");
        JButton selectMultiplayerButton = new JButton("Numero de jogadores");
        JButton playButton = new JButton("PLAY");
        JButton optionsButton = new JButton("Opções");
        JButton okButton1 = new JButton("OK");
        JButton okButton2 = new JButton("OK");

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
        
        multiplayerButton.setBackground(BACK_BUTTONS_COLOR);
        multiplayerButton.setForeground(FONT_COLOR);
        multiplayerButton.setFont(FONT);
        multiplayerButton.addActionListener(e -> {
            this.multiplayer = true;
            Window.setKeyMap();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });

        singlePlayerButton.setBackground(BACK_BUTTONS_COLOR);
        singlePlayerButton.setForeground(FONT_COLOR);
        singlePlayerButton.setFont(FONT);
        singlePlayerButton.addActionListener(e -> {
            this.multiplayer = false;
            Window.setKeyMap();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to key map panel

        selectMultiplayerButton.setBackground(BACK_BUTTONS_COLOR);
        selectMultiplayerButton.setFont(FONT);
        selectMultiplayerButton.setForeground(FONT_COLOR);
        selectMultiplayerButton.addActionListener(e -> {
            Window.setMultiplayer();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to selectPanel

        playButton.setBackground(BACK_BUTTONS_COLOR);
        playButton.setFont(FONT);
        playButton.setForeground(FONT_COLOR);
        playButton.addActionListener(e -> {
            Window.setPlay();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to the game

        optionsButton.setBackground(BACK_BUTTONS_COLOR);
        optionsButton.setFont(FONT);
        optionsButton.setForeground(FONT_COLOR);
        optionsButton.addActionListener(e -> {
            Window.setOptions();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to options panel

        okButton1.setBackground(BACK_BUTTONS_COLOR);
        okButton1.setFont(FONT);
        okButton1.setForeground(FONT_COLOR);
        okButton1.addActionListener(e -> {
            Window.setMenu();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });

        okButton2.setBackground(BACK_BUTTONS_COLOR);
        okButton2.setFont(FONT);
        okButton2.setForeground(FONT_COLOR);
        okButton2.addActionListener(e -> {
            Window.setMenu();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });

        String[] list = {"Easy", "Medium", "Hard", "God"};

        difficulty.setFont(FONT);
        difficulty.setModel(new DefaultComboBoxModel<>(list));
        difficulty.addActionListener(e ->
                difficultyLevel = difficulty.getSelectedIndex());

        menu_panel.setLayout(new GridLayout(5, 1, 0, 10));
        menu_panel.setBackground(BACK_PANELS_COLOR);
        menu_panel.add(nameLabel);
        menu_panel.add(selectMultiplayerButton);
        menu_panel.add(optionsButton);
        menu_panel.add(playButton);
        menu_panel.setOpaque(true);

        optionsPanel.setLayout(new GridLayout(3, 1, 0, 10));
        optionsPanel.setBackground(BACK_PANELS_COLOR);
        optionsPanel.add(selectLabel);
        optionsPanel.add(difficulty);
        optionsPanel.add(okButton1);
        optionsPanel.setOpaque(true);

        multiplayerPanel.setLayout(new GridLayout(2, 1, 0, 10));
        multiplayerPanel.setBackground(BACK_PANELS_COLOR);
        multiplayerPanel.add(multiplayerButton);
        multiplayerPanel.add(singlePlayerButton);
        multiplayerPanel.setOpaque(true);

        controlsPanel.setLayout(new GridLayout(4, 1, 0, 10));
        controlsPanel.setBackground(BACK_PANELS_COLOR);
        controlsPanel.add(keyInstructLabel);
        controlsPanel.add(keyMapLabel);
        controlsPanel.add(pauseLabel);
        controlsPanel.add(okButton2);
        controlsPanel.setOpaque(true);
    }
}