package model;

import view.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends GameEntity {
    public BufferedImage image;
    public boolean collision;
    Rectangle soldArea;
    public Tile(BufferedImage image, boolean collision) {
        super();
        this.image = image;
        this.collision = collision;
        soldArea = new Rectangle(0, 0, GameWindow.TILE_SIZE, GameWindow.TILE_SIZE);
    }


    public int eaten() {
        return 0;
    }
}
