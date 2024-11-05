package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameEntity {
    private int positionX;
    private int positionY;
    protected int width;
    protected int height;
    protected int speed;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    Pair pair = new Pair(positionX, positionY);
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public abstract int eaten();


    public  void move(){};

    Pair getPosition() {
        return pair;
    }

    public boolean intersects(GameEntity other) {
        if (other.positionX == this.positionX + this.width ||
                other.positionX + other.width == this.positionX ||
                other.positionY == this.positionY + this.height ||
                other.positionY + other.height == this.positionY) {
            return true;
        }
        return false;
    }






}
