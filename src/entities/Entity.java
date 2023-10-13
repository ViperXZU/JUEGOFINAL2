package entities;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public  abstract class Entity {
    protected int width,height;
    protected float x,y;
    protected Rectangle2D.Float hitbox;


    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitBox(Graphics g, int xLvlOffset){
        g.setColor(Color.pink);
        g.drawRect((int)hitbox.x - xLvlOffset,(int) hitbox.y, (int)hitbox.width,(int)hitbox.height);
    }

    protected void initHitbox(float x, float y , float width, float height){
        hitbox = new Rectangle2D.Float(x, y, width,height);
    }


    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
    public void setHitbox(int x, int y){
        this.hitbox.x = x;
        this.hitbox.y = y;
    }



}
