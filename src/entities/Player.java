package entities;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;

import static utilz.Constans.PlayerConstants.*;
import static utilz.HelpMethods.*;

public class Player extends Entity{
    // Variables para cambiar / guardar animaciones
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;


    // Variables para movimiento / Ataque
    private boolean right,left,up,down, jump;
    private boolean moving = false, attacking= false;

    private float playerSpeed = 2.0f; // Velocidad del Personaje

    private int[][] lvlData;

    // Variables encuadrar la hitbox en el personaje
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset= 4 * Game.SCALE;

    //Variables Gravedad / SALTO
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    //Clase principal del jugador
    public Player(float x, float y,int width , int height) {
        super(x, y , width, height);
        loadAnimations();
        initHitbox(x,y,20*Game.SCALE,27 * Game.SCALE); // Iniciacion principal de la hitbox
    }

    public void update(){
        updatePos();

        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset),width, height, null);
        //Sacar comentario si quieres que se vea la hitbox  drawHitBox(g);
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

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitbox,lvlData)){
            inAir = true;
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

        if (inAir){
            if (airSpeed < 0) {
                playerAction = JUMP;
            }else
                playerAction = FALLING;
        }

        if (attacking)
            playerAction = ATTACK_1;


        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick =0;
        aniIndex =0;
    }


    //Funcion para actualizar la posicion del personaje dependiendo de la tecla que se presione
    private void updatePos(){
        moving= false;

        if (jump){
            jump();
        }

        if (!left && !right && !inAir ){
            return;
        }

        float xSpeed =0;

       if(left)
            xSpeed -=playerSpeed;
       if(right)
           xSpeed += playerSpeed;

       if (!inAir){
           if (!IsEntityOnFloor(hitbox, lvlData)){
               inAir= true;
           }
       }

       if (inAir){

        if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)){
            hitbox.y +=airSpeed;
            airSpeed += gravity;
            updateXPos(xSpeed);
        }else {
        //Condicional que tiene dentro una funcion condicional que detectara si el personaje se puede mover en una direccion u otra
            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox,airSpeed);
            if (airSpeed > 0)
                resetInAir();
            else
                airSpeed = fallSpeedAfterCollision;
            updateXPos(xSpeed);
        }

       }else
           updateXPos(xSpeed);
       moving = true;


    }

    private void jump() {
        if(inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)){
            hitbox.x +=xSpeed;
        }else {
            hitbox.x = GetEntityXposNextToWall(hitbox, xSpeed);
        }
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
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

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
