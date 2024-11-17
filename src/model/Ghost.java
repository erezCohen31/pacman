package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ghost extends GameEntity {
    public  boolean isFear;
    String name;
    int points=200;
    public BufferedImage image;

    public Ghost(String name,int positionX, int positionY,BufferedImage image) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.image =image;
        this.name = name;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ghosts/" + name + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        collisionOn = true;

    }

    void chasePacMan(PacMan pacMan) {
    }

    void flee() {
    }

    @Override
    public int eaten() {
        return 0;
    }
}
