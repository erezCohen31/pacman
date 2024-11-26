package control;

import model.GameMap;
import model.PacMan;
import model.Points;
import model.TileManager;

import java.awt.*;
import java.util.*;
import java.util.List;

public class FruitManager {

    private final Random random = new Random();
    public Map<String, Integer> fruitList = new HashMap<>();
    List<String> fruitListName = new ArrayList<>();
    public final static List<Point> fruitPosition = new ArrayList<>();
    private Timer despawnTimer;
    Point currentFruitPosition;
    Points currentFruit=new Points();


    public void setFruitPosition() {

        for (int i = 0; i < GameMap.mapPellet.size(); i++) {

            Set<Integer> keys = GameMap.mapPellet.get(i).keySet();
            for (int key:keys ) {
                if (GameMap.mapPellet.get(i).get(key) == null && TileManager.mapTileNum[i][key] == 1) {
                    fruitPosition.add(new Point(i, key));
                }
            }
        }
    }


    private void setFruitList() {
        fruitListName.add("cerise");
        fruitListName.add("fraise");
        fruitListName.add("melon");
        fruitListName.add("orange");
        fruitListName.add("pomme");
        fruitList.put("cerise", 100);
        fruitList.put("fraise", 300);
        fruitList.put("melon", 1000);
        fruitList.put("orange", 500);
        fruitList.put("pomme", 700);
    }

    public void startFruit() {

        setFruitList();
//        startFruitTimer();
    }


    public Points spawnFruit() {
        setFruitPosition();
        // Sélectionner aléatoirement un emplacement dans les points possibles de la carte

        if(!fruitPosition.isEmpty()) {
            int randomPosition = random.nextInt(fruitPosition.size());

             currentFruitPosition = fruitPosition.get(randomPosition);
            // Créer et positionner un fruit
            String name = getRandomFruit();
            Points fruit = new Points(currentFruitPosition.x, currentFruitPosition.y, name, fruitList.get(name));
            fruit.setSolidArea();
            Map<Integer, Points> posY = new HashMap<>();
            posY.put(currentFruitPosition.y, fruit);
            GameMap.mapPellet.get(currentFruitPosition.x).put(currentFruitPosition.y, fruit);
            startDespawnTimer(() -> {
                GameMap.mapPellet.get(currentFruitPosition.x).put(currentFruitPosition.y, null);
                System.out.println("Fruit disparu !");
            });
            return fruit;

        }
return null;
    }

    private String getRandomFruit() {
        return fruitListName.get(random.nextInt(fruitListName.size()));


    }
    public void startDespawnTimer(Runnable onDespawn) {
        despawnTimer = new Timer();
        despawnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                despawn();
                onDespawn.run();
            }
        }, 10000); // 10 secondes
    }

    private void despawn() {
        if (despawnTimer != null) {
            despawnTimer.cancel();
            GameMap.mapPellet.get(currentFruitPosition.x).put(currentFruitPosition.y,null);
        }
    }
public void appearFruit(){   if (PacMan.score > 0 && PacMan.score % 100 == 0) {
    boolean isContain = false;
    for (int i = 0; i < GameMap.mapPellet.size(); i++) {
        if (GameMap.mapPellet.get(i).containsValue(currentFruit)) {
            isContain = true;
            break;
        }
    }
    if (!isContain) {
        currentFruit = spawnFruit();

    }
}}
}
