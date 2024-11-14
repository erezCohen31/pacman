package model;

import control.GameController;
import view.GameWindow;

import java.util.*;

public class FruitManger {

    private Timer fruitTimer = new Timer();;
    private Random random = new Random();
    public static Stack<Point> fruits = new Stack<>();
    Map<String, Integer> fruitList = new HashMap<>();
    List<String> fruitListName = new ArrayList<>();
    public final static List<Pair> fruitPosition = new ArrayList<>();

    public void setFruitPosition() {

        for (int i = 0; i < GameController.mapPellet.size(); i++) {

            Set<Integer> keys = GameController.mapPellet.get(i).keySet();
            for (int key:keys ) {
                if (GameController.mapPellet.get(i).get(key) == null && TileManager.mapTileNum[i][key] == 1) {
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
        startFruitTimer();
    }

    public void startFruitTimer() {
        fruitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                setFruitPosition();
                spawnFruit();
            }
        }, 5000, 5000); // 25 secondes d'intervalle
    }

    private void spawnFruit() {

        // Sélectionner aléatoirement un emplacement dans les points possibles de la carte

        if(!fruitPosition.isEmpty()) {
            int randomPosition = random.nextInt(fruitPosition.size());

            Pair currentFruitPosition = fruitPosition.get(randomPosition);
            // Créer et positionner un fruit
            String name = getRandomFruit();
            Point fruit = new Point(currentFruitPosition.getX(), currentFruitPosition.getY(), name, fruitList.get(name));
            Map<Integer, Point> posY = new HashMap<>();
            posY.put(currentFruitPosition.getY(), fruit);
            GameController.mapPellet.get(currentFruitPosition.getX()).put(currentFruitPosition.getY(), fruit);

        }

    }

    private String getRandomFruit() {
        return fruitListName.get(random.nextInt(fruitListName.size()));


    }

}
