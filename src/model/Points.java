package model;

import utils.ImageLoader;
import view.GameWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Points extends GameEntity {
    int points;
    boolean isEaten;
   public BufferedImage image;
    String name;

    public Points() {
    }

    ;

    public Points(int positionX, int positionY, String name, int points) {
        ImageLoader loader = ImageLoader.getInstance();
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.name = name;
        image = loader.loadImage("/fruits/" + name + ".png");
        this.points = points;
        collisionOn = true;

    }



    public int eaten() {
        return 0;
    }

    public void setSolidArea() {
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = GameWindow.TILE_SIZE;
        solidArea.height = GameWindow.TILE_SIZE;

    }


    public int getPoints() {
        return points;
    }
}
