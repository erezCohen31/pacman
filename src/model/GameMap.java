package model;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final List<Pellet> pelletList = new ArrayList<>();
    private final List<Pair> wallList = new ArrayList<>();


    public int getRemainingPellets() {
        return pelletList.size();
    }

    public boolean isWall(int x, int y) {
        // Exemple basique pour vérifier la présence d'un mur
        return wallList.stream().anyMatch(wall -> wall.equals(new Pair(x, y)));
    }

    public void spawnFruit() {
        // Logique d’apparition des fruits
    }
}
