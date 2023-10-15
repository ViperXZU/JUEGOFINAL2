package entities;

import main.Game;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.EventListener;

import static utilz.Constans.EnemyConstans.*;
import static utilz.HelpMethods.*;
import static utilz.Constans.Directions.*;

public abstract class Enemy extends Entity{
    protected int aniIndex, enemyState, enemyType;
    protected int aniTick, aniSpeed = 25;
    protected boolean firstUpdate = true;
    protected boolean inAir;
    protected float fallSpeed;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = 0.35f * Game.SCALE;
    protected int walkDir = LEFT;
    protected int tileY;
    protected int attackDistance = Game.TILES_SIZE;
    protected int maxHealth;
    protected int currentHealth;
    protected boolean active = true;
    protected boolean attackChecked;



    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
        maxHealth= GetMaxHealth(enemyType);
        currentHealth = maxHealth;
    }

    protected void updateInAir(int [][] lvlData){
        if (CanMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)) {
            hitbox.y += fallSpeed;
            fallSpeed += gravity;

        } else {
            inAir = false;
            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, fallSpeed,0);
            tileY = (int) (hitbox.y / Game.TILES_SIZE);
        }
    }

    protected void firstUpdateCheck(int[][] lvlData){
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
        firstUpdate = false;
    }


    //MUY RESUMIDAMENTE ES UNA FUNCION BOOLEANA QUE DETECTA CUANDO EL ENEMIGO PUEDE O NO VER AL JUGADOR
    protected boolean canSeePlayer(int[][] lvlData, Player player){
        int playerTileY = (int) ((player.getHitbox().y+25) / Game.TILES_SIZE);
        if (playerTileY == tileY)
            if (isPlayerInRange(player))//Aqui estamos chekeando que el jugador esta dentro del rango visible del enemigo
            {if (IsSightClear(lvlData, hitbox, player.hitbox, tileY))// y aqui revisamos si no hay nada que se interponga
                    return true;  //despues si pasa los filtros es verdadero
            }
        return false;
    }
    // Basicamente para que el enemigo sepa a donde dirigirse
    protected void turnTowardsPlayer(Player player){
        if (player.hitbox.x > hitbox.x)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }


    // Verifica que el jugador esta dentro del rango del enemigo
    protected boolean isPlayerInRange(Player player) {
        int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
        return absValue <= attackDistance*5;
    }

    // Verifica que el jugador esta lo suficientemente cerca para atacar
    protected boolean isPlayerCloseForAttack(Player player){
        int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
        return absValue <= attackDistance;
    }

    // Todo lo que conlleva el movimiento del enemigo
    protected void move(int[][] lvlData){
        float xSpeed = 0;

        if (walkDir == LEFT)
            xSpeed = -walkSpeed;
        else
            xSpeed = walkSpeed;

        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y -1, hitbox.width, hitbox.height, lvlData))
            if (IsFloor(hitbox, xSpeed, lvlData)) {
                hitbox.x += xSpeed;
                return;
            }
        changeWalkDir();
    }

    //Para que cambiar el estado y aprovechando reiniciar la animacion
    protected void newState(int enemyState){
        this.enemyState=enemyState;
        aniTick=0;
        aniIndex=0;
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
                switch (enemyState){
                    case ATTACK, HIT -> enemyState = IDLE;
                    case DEAD -> active = false;
                }
            }
        }
    }

    public void hurt(int Damage) {
        currentHealth -= Damage;

        if (currentHealth <= 0){
            newState(DEAD);
        }else
            newState(HIT);
    }


    protected void checkEnemyHit(Rectangle2D.Float attackBox,Player player) {
        if (attackBox.intersects(player.hitbox))
            player.changeHealth(-GetEnemyDmg(enemyType));
        attackChecked = true;
    }

    protected void changeWalkDir() {
        if (walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;

    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }


    public boolean isActive() {
        return active;
    }
}
