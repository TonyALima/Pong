package menu;

import game.Window;

import javax.swing.*;
import java.awt.*;

public class Menu {

    private final JButton SELECT_MULTIPLAYER_BUTTON;
    private final JButton PLAY_BUTTON;
    private final JButton OPTIONS;
    private final JButton SINGLE_PLAYER_BUTTON;
    private final JButton MULTIPLAYER_BUTTON;

    private final JPanel MENU_PANEL;
    private final JPanel OPTIONS_PANEL;
    private final JPanel MULTIPLAYER_PANEL;

    private final JLabel NAME_LABEL;
    private final JLabel SELECT_LABEL;

    private final JComboBox<String> DIFFICULTY;
    private final Color BACK_BUTTONS_COLOR = new Color(100, 100, 100);
    private final Color BACK_PANELS_COLOR = new Color(200, 200, 200);
    private final Color FONT_COLOR = new Color(220, 220, 220);
    private final Font FONT = new Font("Times", Font.BOLD, 15);
    private final Thread windowThread;
    private boolean multiplayer;

    // Constructor
    public Menu(Thread windowThread) {
        this.SELECT_LABEL = new JLabel("Dificuldade");
        this.NAME_LABEL = new JLabel("Bom dia");

        this.SINGLE_PLAYER_BUTTON = new JButton("Single player");
        this.MULTIPLAYER_BUTTON = new JButton("Multiplayer");
        this.SELECT_MULTIPLAYER_BUTTON = new JButton("Numero de jogadores");
        this.PLAY_BUTTON = new JButton("PLAY");
        this.OPTIONS = new JButton("Opções");

        this.DIFFICULTY = new JComboBox<>();
        startComponents();
        this.MENU_PANEL = new JPanel();
        this.OPTIONS_PANEL = new JPanel();
        this.MULTIPLAYER_PANEL = new JPanel();
        startPanels();
        this.windowThread = windowThread;
    }

    // Getters
    public JPanel getMENU_PANEL() {
        return MENU_PANEL;
    }

    public JPanel getOPTIONS_PANEL() {
        return OPTIONS_PANEL;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public JPanel getMULTIPLAYER_PANEL() {
        return MULTIPLAYER_PANEL;
    }

    // Methods
    private void startComponents() {
        NAME_LABEL.setFont(FONT);
        SELECT_LABEL.setFont(FONT);

        MULTIPLAYER_BUTTON.setBackground(BACK_BUTTONS_COLOR);
        MULTIPLAYER_BUTTON.setForeground(FONT_COLOR);
        MULTIPLAYER_BUTTON.setFont(FONT);
        MULTIPLAYER_BUTTON.addActionListener(e -> {
            this.multiplayer = true;
            Window.setMenu();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });

        SINGLE_PLAYER_BUTTON.setBackground(BACK_BUTTONS_COLOR);
        SINGLE_PLAYER_BUTTON.setForeground(FONT_COLOR);
        SINGLE_PLAYER_BUTTON.setFont(FONT);
        SINGLE_PLAYER_BUTTON.addActionListener(e -> {
            this.multiplayer = false;
            Window.setMenu();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });

        SELECT_MULTIPLAYER_BUTTON.setBackground(BACK_BUTTONS_COLOR);
        SELECT_MULTIPLAYER_BUTTON.setFont(FONT);
        SELECT_MULTIPLAYER_BUTTON.setForeground(FONT_COLOR);
        SELECT_MULTIPLAYER_BUTTON.addActionListener(e -> {
            Window.setMultiplayer();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to selectPanel

        PLAY_BUTTON.setBackground(BACK_BUTTONS_COLOR);
        PLAY_BUTTON.setFont(FONT);
        PLAY_BUTTON.setForeground(FONT_COLOR);
        PLAY_BUTTON.addActionListener(e -> {
            Window.setPlay();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to the game

        OPTIONS.setBackground(BACK_BUTTONS_COLOR);
        OPTIONS.setFont(FONT);
        OPTIONS.setForeground(FONT_COLOR);
        OPTIONS.addActionListener(e -> {
            Window.setOptions();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });

        String[] list = {"Easy", "Medium", "Hard", "God"};

        DIFFICULTY.setFont(FONT);
        DIFFICULTY.setModel(new DefaultComboBoxModel<>(list));
        DIFFICULTY.addActionListener(e -> {

            Window.setMenu();
            synchronized (windowThread) {
                windowThread.notify();
            }
        });
    }

    private void startPanels() {
        GroupLayout menuLayout = new GroupLayout(MENU_PANEL);
        MENU_PANEL.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
                menuLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGap(20)
                        .addComponent(NAME_LABEL)
                        .addComponent(SELECT_MULTIPLAYER_BUTTON)
                        .addComponent(OPTIONS)
                        .addComponent(PLAY_BUTTON)
                        .addGap(20)
        );
        menuLayout.setVerticalGroup(
                menuLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(menuLayout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(NAME_LABEL)
                                .addGap(10)
                                .addComponent(SELECT_MULTIPLAYER_BUTTON)
                                .addGap(10)
                                .addComponent(OPTIONS)
                                .addGap(10)
                                .addComponent(PLAY_BUTTON)
                                .addGap(20))

        );
        MENU_PANEL.setBackground(BACK_PANELS_COLOR);

        GroupLayout selectLayout = new GroupLayout(OPTIONS_PANEL);
        OPTIONS_PANEL.setLayout(selectLayout);
        selectLayout.setHorizontalGroup(
                selectLayout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(
                                selectLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(SELECT_LABEL)
                                        .addComponent(DIFFICULTY))
                        .addGap(10)
        );
        selectLayout.setVerticalGroup(
                selectLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(selectLayout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(SELECT_LABEL)
                                .addGap(10)
                                .addComponent(DIFFICULTY)
                                .addGap(20))

        );
        OPTIONS_PANEL.setBackground(BACK_PANELS_COLOR);

        GroupLayout multiplayerLayout = new GroupLayout(MULTIPLAYER_PANEL);
        MULTIPLAYER_PANEL.setLayout(multiplayerLayout);
        multiplayerLayout.setHorizontalGroup(
                multiplayerLayout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(
                                multiplayerLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(MULTIPLAYER_BUTTON)
                                        .addComponent(SINGLE_PLAYER_BUTTON))
                        .addGap(10)
        );
        multiplayerLayout.setVerticalGroup(
                multiplayerLayout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(MULTIPLAYER_BUTTON)
                        .addGap(10)
                        .addComponent(SINGLE_PLAYER_BUTTON)
                        .addGap(20)
        );
        MULTIPLAYER_PANEL.setBackground(BACK_PANELS_COLOR);
    }
}