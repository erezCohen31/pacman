package model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Melon extends Fruit{
    @Override
    void loadPelletImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/fruits/melon.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
