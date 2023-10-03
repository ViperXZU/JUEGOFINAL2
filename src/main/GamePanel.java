package main;

import inputs.Keyboardinputs;
import inputs.MouseInputs;
import utilz.Constans;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constans.PlayerConstants.*;
import static utilz.Constans.Direction.*;

public class GamePanel  extends JPanel {

    private  MouseInputs mouseInputs;
    private  Game game;
    public GamePanel(Game game){
        this.game = game;
        setPanelSize();

        mouseInputs = new MouseInputs(this );
        addKeyListener(new Keyboardinputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800); // Seteo de las dimensiones del Panel(NO DE LA VENTANA)
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public void updateGame() {

    }

    public Game getGame() {
        return game;
    }
}
