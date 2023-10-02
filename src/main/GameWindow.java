package main;

import javax.swing.*;
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
    }
}
