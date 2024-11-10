package model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Orange extends Fruit{
    @Override
    void loadPelletImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/fruits/orange.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
