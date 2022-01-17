package menu;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class GameButton extends JButton{
    
    public GameButton(String str) {
        super(str);
        this.setBackground(new Color(100, 100, 100));
        this.setForeground(new Color(220, 220, 220));
        this.setFont(new Font("Times", Font.BOLD, 15));
    }
}
