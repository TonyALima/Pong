package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tick implements Runnable, KeyListener {

    private boolean right, left, pause;

    @Override
    public void run() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
        }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            pause = !pause;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
        }
    }
}
