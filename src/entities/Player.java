package entities;

import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constans.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int PLAYER_HIGHT, PLAYER_WIDTH;

    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;

    private boolean right,left,up,down;
    private boolean moving = false, attacking= false;
    private float playerSpeed = 2.0f;


    public Player(float x, float y,int PLAYER_WIDTH, int PLAYER_HIGHT) {
        super(x, y);
        this.PLAYER_HIGHT = PLAYER_HIGHT;
        this.PLAYER_WIDTH = PLAYER_WIDTH;
        loadAnimations();
    }

    public void update(){
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y,PLAYER_WIDTH,PLAYER_HIGHT, null);
    }

    private void loadAnimations() {

            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[9][6];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
                }
            }
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    public void resetDirBooleans(){
        up = false;
        down = false;
        left= false;
        right=false;
    }

    public void setAttack(boolean attack){
        this.attacking = attack;
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (attacking)
            playerAction = ATTACK_1;

        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick =0;
        aniIndex =0;
    }

    private void updatePos(){
        moving= false;

       if(left && !right){
            x-= playerSpeed;
            moving = true;
       }else if(right && !left){
           x+= playerSpeed;
           moving = true;
       }
        if(up && !down){
            y-=playerSpeed;
            moving = true;
        }else if(down && !up){
            y+=playerSpeed;
            moving = true;
        }
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
