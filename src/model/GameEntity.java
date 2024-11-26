package model;

import control.CollisionChecker;
import view.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class GameEntity {

    public int speed;
    public Point pos = new Point();
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Direction direction;
    public Rectangle solidArea = new Rectangle();
    public boolean collisionOn = false;
    public Map<Direction, BufferedImage[]> directionImages = new HashMap<>();

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public CollisionChecker cChecker;



    public int getPositionX() {
        return pos.x;
    }

    public void setPositionX(int positionX) {
        pos.x = positionX;

    }

    public Point getPos() {
        return pos;
    }

    public int getPositionY() {
        return pos.y;
    }

    public void setPositionY(int positionY) {
        this.pos.y = positionY;
    }



    public void setPosition(int positionX, int positionY) {
        this.pos.x = positionX;
        this.pos.y = positionY;
    }

}