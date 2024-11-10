package model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Strawberry extends Fruit{
    @Override
    void loadPelletImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/fruits/fraise.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
