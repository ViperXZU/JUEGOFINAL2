package main;

import inputs.Keyboardinputs;
import inputs.MouseInputs;
import javax.swing.*;
import java.awt.*;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;


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
        Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT); // Seteo de las dimensiones del Panel(NO DE LA VENTANA)
        setPreferredSize(size);
        System.out.println("Dimensiones: (LARGO)"+ GAME_HEIGHT + " (ANCHO)"+GAME_WIDTH);
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
