package model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Apple extends Fruit{
    @Override
    void loadPelletImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/fruits/pomme.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
