package model;

import view.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pellet extends Point {





    public Pellet(int x,int y,int points) {
        this.points = points;
        setPositionX(x);
        setPositionY(y);
        loadPelletImage();
        collisionOn=true;


    }




    private void loadPelletImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/pellets/pellet.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
