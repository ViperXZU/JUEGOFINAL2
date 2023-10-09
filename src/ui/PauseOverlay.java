package ui;

import gamestates.Gamestate;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constans.UI.PauseButtons.*;


public class PauseOverlay {


    private BufferedImage backgroundImg;
    private int bgX,bgY,bgW,bgH;
    private SoundButton musicButton, sfxButton;

    public PauseOverlay(){

        loadBackground();
        createSoundButtons();

    }

    private void createSoundButtons() {
        int soundX = (int) (450*Game.SCALE);
        int musicY = (int) (140*Game.SCALE);
        int sfxY = (int) (186*Game.SCALE);

        musicButton = new SoundButton(soundX,musicY,SOUND_SIZE,SOUND_SIZE,0);
        sfxButton = new SoundButton(soundX,sfxY,SOUND_SIZE,SOUND_SIZE, 1);
    }

    // Carga de la imagen y establecer las medidas y lugar
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND_MENU);
        bgW = backgroundImg.getWidth();
        bgH = backgroundImg.getHeight();
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (55 * Game.SCALE);

    }

    public void update(){
        musicButton.update();
        sfxButton.update();


    }
    // Dibujar la imagen dentro del panel
    public void draw(Graphics g){
        g.drawImage(backgroundImg,bgX,bgY,bgW,bgH,null);

        musicButton.draw(g);
        sfxButton.draw(g);

    }

    public void mousePressed(MouseEvent e) {
        if(isIn(e,musicButton))
            musicButton.setMousePressed(true);
        else if (isIn(e,sfxButton))
            sfxButton.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e,musicButton)){
            if (musicButton.isMousePressed())
                musicButton.setMuted(!musicButton.isMuted());

        }else if (isIn(e,sfxButton))
            if (sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e){}

    private boolean isIn(MouseEvent e, PauseButtons b){
        return (b.getBounds().contains(e.getX(),e.getY()));
    }
}
