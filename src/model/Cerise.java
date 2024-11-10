package model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Cerise extends Fruit{
    @Override
    void loadPelletImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/fruits/cerise.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
