package menu;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;

public class GamePanel extends JPanel{
    
    public GamePanel(JComponent[] comps) {
        this.setLayout(new GridLayout(comps.length, 1, 0, 10));
        this.setBackground(new Color(200, 200, 200));
        for (int i = 0; i < comps.length; i++){
            this.add(comps[i]);
        }
        this.setOpaque(true);
    }

}
