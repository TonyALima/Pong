package menu;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import java.awt.GridLayout;
import java.awt.Color;

public class GamePanel extends JPanel{

    public GamePanel() {
        this.setBackground(new Color(200, 200, 200));
        this.setOpaque(true);
        this.setPreferredSize(new DimensionUIResource(30, 30));
    }
    
    public GamePanel(JComponent[] comps) {
        this.setLayout(new GridLayout(comps.length, 1, 0, 10));
        this.setBackground(new Color(200, 200, 200));
        for (int i = 0; i < comps.length; i++){
            this.add(comps[i]);
        }
        this.setOpaque(true);
    }

}
