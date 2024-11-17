package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {

    public final static Map<Integer, Map<Integer, Point>> mapPellet = new HashMap<>();


    public void setPellets() {

        for (int i = 0; i < 25; i++) {
            Map<Integer, Point> yPellet = new HashMap<>();
            for (int j = 5; j < 28; j++) {
                if (TileManager.mapTileNum[i][j] == 1) {


                    yPellet.put(j, new Pellet(i, j, 10));

                    yPellet.get(j).setSolidArea();



                } else if (TileManager.mapTileNum[i][j] == 16) {


                    yPellet.put(j, new BigPellet(i, j, 50));

                    yPellet.get(j).setSolidArea();




                }

            }
            mapPellet.put(i, yPellet);
        }

    }

}
