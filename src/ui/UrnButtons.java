package ui;


import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constans.UI.URMButtons.*;

public class UrnButtons extends PauseButtons{

    private BufferedImage[] imgs;
    private int index;
    private boolean mouseOver, mousePressed;



    public UrnButtons(int x, int y, int width, int height, int index) {
        super(x, y, width, height);
        this.index = index;
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS);
        imgs = new BufferedImage[3];
        for (int i =0; i< imgs.length;i++)
            imgs[i] = temp.getSubimage(0, i*URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
    }

    public void update(){

    }
    
    public void draw(Graphics g){
        g.drawImage(imgs[index],x,y,URM_SIZE,URM_SIZE,null);
    }

    public void resetBooleans(){
        mousePressed=false;
        mouseOver=false;
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
