package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Constans.Direction.*;

public class Keyboardinputs implements KeyListener {

    private GamePanel gamePanel;

    public  Keyboardinputs(GamePanel gamePanel){

        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                gamePanel.setDirection(UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.setDirection(LEFT);
                break;
            case KeyEvent.VK_D:
                gamePanel.setDirection(RIGHT);
                break;
            case KeyEvent.VK_S:
                gamePanel.setDirection(DOWN);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
            case KeyEvent.VK_S:
                gamePanel.setMoving(false);
                break;
        }
    }
}
