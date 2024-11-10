package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameEntity {
    private int positionX;
    private int positionY;
    protected int width;
    protected int height;
    public int speed;
    public String direction;
    public Rectangle solidArea=new Rectangle();
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public BufferedImage image;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public abstract int eaten();

    public void move() {
        // Implémentation à définir dans les sous-classes si nécessaire
    }

    public boolean intersects(GameEntity other) {
        return this.positionX < other.positionX + other.width &&
                this.positionX + this.width > other.positionX &&
                this.positionY < other.positionY + other.height &&
                this.positionY + this.height > other.positionY;
    }
}
