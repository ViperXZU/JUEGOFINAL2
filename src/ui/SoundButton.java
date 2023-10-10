package ui;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;

import static utilz.Constans.UI.PauseButtons.*;

public class SoundButton extends PauseButtons {

    private int rowIndex, colmIndex;
    private BufferedImage[][] soundImgs;
    private boolean mouseOver,mousePressed;
    private boolean muted;

    public SoundButton(int x, int y, int width, int height,int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadSoundImgs();
    }

    private void loadSoundImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][2];
        for (int j = 0; j < soundImgs.length; j++)
            for (int i = 0; i<soundImgs[j].length; i++)
                soundImgs[j][i] = temp.getSubimage(j* SOUND_SIZE_DEFAULT,i* SOUND_SIZE_DEFAULT,SOUND_SIZE_DEFAULT,SOUND_SIZE_DEFAULT);
    }

    public void update(){
        if (muted)
            colmIndex = 1;
        else
            colmIndex= 0;
    }

    public void draw(Graphics g){
        g.drawImage(soundImgs[colmIndex][rowIndex],x,y,width,height,null);
    }

    public void resetBooleans(){
        mouseOver= false;
        mousePressed= false;

    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
}
