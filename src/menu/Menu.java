package menu;

import dataManipulation.Record;
import game.Window;
import players.Player;

import javax.swing.*;
import java.awt.*;

public class Menu extends Record {

    private final JButton SELECT_BUTTON;
    private final JButton REMOVE_BUTTON;
    private final JButton ADD_BUTTON;
    private final JButton PLAY_BUTTON;
    private final JButton RANKING_BUTTON;
    private final JPanel MENU_PANEL;
    private final JPanel SELECT_PANEL;
    private final JPanel ADD_PANEL;
    private final JPanel REMOVE_PANEL;
    private final JLabel NAME_LABEL;
    private final JLabel NEW_NAME_LABEL;
    private final JLabel REMOVE_LABEL;
    private final JLabel SELECT_LABEL;
    private final JComboBox<String> PLAYERS;
    private final JTextField NEW_PLAYER;
    private final Color BACK_BUTTONS_COLOR = new Color(100, 100, 100);
    private final Color BACK_PANELS_COLOR = new Color(200, 200, 200);
    private final Color FONT_COLOR = new Color(220, 220, 220);
    private final Font FONT = new Font("Times", Font.BOLD, 15);
    private final Thread windowThread;

    // Constructor
    public Menu(Thread windowThread) {
        playerDataExists();
        this.SELECT_LABEL = new JLabel("Escolha");
        this.REMOVE_LABEL = new JLabel("        Remover        ");
        this.NAME_LABEL = new JLabel("Bom dia");
        this.NEW_NAME_LABEL = new JLabel("  Seu nome:  ");
        this.SELECT_BUTTON = new JButton("Selecionar jogador");
        this.ADD_BUTTON = new JButton("Adicionar jogador");
        this.REMOVE_BUTTON = new JButton("Remover jogador");
        this.PLAY_BUTTON = new JButton("PLAY");
        this.RANKING_BUTTON = new JButton("RANKING");
        this.PLAYERS = new JComboBox<>();
        this.NEW_PLAYER = new JTextField();
        startComponents();
        this.MENU_PANEL = new JPanel();
        this.SELECT_PANEL = new JPanel();
        this.ADD_PANEL = new JPanel();
        this.REMOVE_PANEL = new JPanel();
        startPanels();
        this.windowThread = windowThread;
        Window.setSelect();
    }

    // Getters
    public JPanel getMENU_PANEL() {
        return MENU_PANEL;
    }

    public JPanel getSELECT_PANEL() {
        return SELECT_PANEL;
    }

    public JPanel getADD_PANEL() {
        return ADD_PANEL;
    }

    public JPanel getREMOVE_PANEL() {
        return REMOVE_PANEL;
    }

    // Methods
    private void startComponents() {
        NEW_NAME_LABEL.setFont(FONT);
        REMOVE_LABEL.setFont(FONT);
        NAME_LABEL.setFont(FONT);
        SELECT_LABEL.setFont(FONT);

        SELECT_BUTTON.setBackground(BACK_BUTTONS_COLOR);
        SELECT_BUTTON.setFont(FONT);
        SELECT_BUTTON.setForeground(FONT_COLOR);
        SELECT_BUTTON.addActionListener(e -> {
            Window.setSelect();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to selectPanel

        ADD_BUTTON.setBackground(BACK_BUTTONS_COLOR);
        ADD_BUTTON.setFont(FONT);
        ADD_BUTTON.setForeground(FONT_COLOR);
        ADD_BUTTON.addActionListener(e -> {
            Window.setAdd();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to addPanel

        REMOVE_BUTTON.setBackground(BACK_BUTTONS_COLOR);
        REMOVE_BUTTON.setFont(FONT);
        REMOVE_BUTTON.setForeground(FONT_COLOR);
        REMOVE_BUTTON.addActionListener(e -> {
            Window.setRemove();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to removePanel

        RANKING_BUTTON.setBackground(BACK_BUTTONS_COLOR);
        RANKING_BUTTON.setFont(FONT);
        RANKING_BUTTON.setForeground(FONT_COLOR);
        RANKING_BUTTON.addActionListener(e -> {
            System.out.println("Ranking working");
        });

        PLAY_BUTTON.setBackground(BACK_BUTTONS_COLOR);
        PLAY_BUTTON.setFont(FONT);
        PLAY_BUTTON.setForeground(FONT_COLOR);
        PLAY_BUTTON.addActionListener(e -> {
            Window.setPlay();
            synchronized (windowThread) {
                windowThread.notify();
            }
        }); // go to the game

        NEW_PLAYER.setFont(FONT);
        NEW_PLAYER.addActionListener(e -> {
            addPlayer(NEW_PLAYER.getText());
            /*
            String command = "java -jar " + new File("").getAbsolutePath() + "/Pong.jar";
            try {
                Runtime.getRuntime().exec(command);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
             */
            System.exit(0);
        });

        String[] list = new String[getAmountOfPlayers()];
        String[][] listPlayers = listPlayers();
        for (int i = 0; i < getAmountOfPlayers(); i++)
            list[i] = listPlayers[i][0];

        PLAYERS.setFont(FONT);
        PLAYERS.setModel(new DefaultComboBoxModel<>(list));
        PLAYERS.addActionListener(e -> {
            int numberPlayer = PLAYERS.getSelectedIndex();
            if (Window.isRemove()) {
                removePlayer(numberPlayer);
                /*
                String command = "java -jar " + new File("").getAbsolutePath() + "/Pong.jar";
                try {
                    Runtime.getRuntime().exec(command);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                 */
                System.exit(0);
            } else if (Window.isSelect()) {
                Player.setNumberPlayer(numberPlayer);
                Player.setName(listPlayers[numberPlayer][0]);
                Player.setRecord(Integer.parseInt(listPlayers[numberPlayer][1]));
            }
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
                        .addComponent(SELECT_BUTTON)
                        .addComponent(PLAY_BUTTON)
                        .addGap(20)
        );
        menuLayout.setVerticalGroup(
                menuLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(menuLayout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(NAME_LABEL)
                                .addGap(10)
                                .addComponent(SELECT_BUTTON)
                                .addGap(10)
                                .addComponent(PLAY_BUTTON)
                                .addGap(20))

        );
        MENU_PANEL.setBackground(BACK_PANELS_COLOR);

        GroupLayout selectLayout = new GroupLayout(SELECT_PANEL);
        SELECT_PANEL.setLayout(selectLayout);
        selectLayout.setHorizontalGroup(
                selectLayout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(
                                selectLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(SELECT_LABEL)
                                        .addComponent(PLAYERS)
                                        .addComponent(ADD_BUTTON)
                                        .addComponent(REMOVE_BUTTON)
                                        .addComponent(RANKING_BUTTON))
                        .addGap(10)
        );
        selectLayout.setVerticalGroup(
                selectLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(selectLayout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(SELECT_LABEL)
                                .addGap(10)
                                .addComponent(PLAYERS)
                                .addGap(10)
                                .addComponent(ADD_BUTTON)
                                .addGap(10)
                                .addComponent(REMOVE_BUTTON)
                                .addGap(10)
                                .addComponent(RANKING_BUTTON)
                                .addGap(20))

        );
        SELECT_PANEL.setBackground(BACK_PANELS_COLOR);

        GroupLayout addLayout = new GroupLayout(ADD_PANEL);
        ADD_PANEL.setLayout(addLayout);
        addLayout.setHorizontalGroup(
                addLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(addLayout.createSequentialGroup()
                                .addGap(20)
                                .addGroup(addLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(NEW_NAME_LABEL)
                                        .addComponent(NEW_PLAYER))
                                .addGap(20))
        );
        addLayout.setVerticalGroup(
                addLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(addLayout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(NEW_NAME_LABEL)
                                .addGap(5)
                                .addComponent(NEW_PLAYER)
                                .addGap(10))
        );
        ADD_PANEL.setBackground(BACK_PANELS_COLOR);

        GroupLayout removeLayout = new GroupLayout(REMOVE_PANEL);
        REMOVE_PANEL.setLayout(removeLayout);
        removeLayout.setHorizontalGroup(
                removeLayout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(
                                removeLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(REMOVE_LABEL)
                                        .addComponent(PLAYERS))
                        .addGap(10)
        );
        removeLayout.setVerticalGroup(
                removeLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(removeLayout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(REMOVE_LABEL)
                                .addGap(5)
                                .addComponent(PLAYERS)
                                .addGap(10))
        );
        REMOVE_PANEL.setBackground(BACK_PANELS_COLOR);
    }
}