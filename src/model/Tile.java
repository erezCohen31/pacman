package model;

import java.awt.image.BufferedImage;

public class Tile extends GameEntity {
    public BufferedImage image;
    public boolean collision = false;
    public Tile(BufferedImage image, boolean collision) {
        this.image = image;
        this.collision = collision;
    }

    @Override
    public int eaten() {
        return 0;
    }
}
