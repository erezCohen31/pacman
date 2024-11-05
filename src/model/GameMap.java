package model;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    int width;
    int height;
    List<Pellet> pelletList = new ArrayList<>();
    List<Pair> wallList = new ArrayList<>();
    List<Fruit> fruitList = new ArrayList<>();

    void isWall(int x, int y) {
    }

    int getRemainingPellets() {
        return pelletList.size();
    }
    void spawnFruit(){}
}
