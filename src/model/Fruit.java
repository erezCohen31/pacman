package model;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Map;

public abstract class Fruit extends GameEntity {
    int points;
    boolean isEaten;


void appear(){}
void disappear(){}



    @Override
    public int eaten() {
        return points;
    }
    abstract void loadPelletImage();
}
