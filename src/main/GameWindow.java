package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.nio.file.FileAlreadyExistsException;

public class GameWindow {
    private JFrame Jframe;
    public GameWindow(GamePanel gamePanel){

        Jframe = new JFrame();

        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.add(gamePanel);
        Jframe.setLocationRelativeTo(null);
        Jframe.setResizable(false);
        Jframe.pack();
        Jframe.setVisible(true);
        Jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
