package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.nio.file.FileAlreadyExistsException;

public class GameWindow {
    private final JFrame Jframe;
    public GameWindow(GamePanel gamePanel){

        Jframe = new JFrame();

        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.add(gamePanel);
        Jframe.setResizable(false);
        Jframe.pack();
        Jframe.setLocationRelativeTo(null);
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
