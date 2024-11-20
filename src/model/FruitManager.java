package model;

import java.util.*;

public class FruitManager {

    private Random random = new Random();
    public Map<String, Integer> fruitList = new HashMap<>();
    List<String> fruitListName = new ArrayList<>();
    public final static List<Pair> fruitPosition = new ArrayList<>();
    private Timer despawnTimer;
    Pair currentFruitPosition;
    Points currentFruit=new Points();


    public void setFruitPosition() {

        for (int i = 0; i < GameMap.mapPellet.size(); i++) {

            Set<Integer> keys = GameMap.mapPellet.get(i).keySet();
            for (int key:keys ) {
                if (GameMap.mapPellet.get(i).get(key) == null && TileManager.mapTileNum[i][key] == 1) {
                    fruitPosition.add(new Pair(i, key));
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
            Points fruit = new Points(currentFruitPosition.getX(), currentFruitPosition.getY(), name, fruitList.get(name));
            fruit.setSolidArea();
            Map<Integer, Points> posY = new HashMap<>();
            posY.put(currentFruitPosition.getY(), fruit);
            GameMap.mapPellet.get(currentFruitPosition.getX()).put(currentFruitPosition.getY(), fruit);
            startDespawnTimer(() -> {
                GameMap.mapPellet.get(currentFruitPosition.getX()).put(currentFruitPosition.getY(), null);
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
            GameMap.mapPellet.get(currentFruitPosition.getX()).put(currentFruitPosition.getY(),null);
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
