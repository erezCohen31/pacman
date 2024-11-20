package model;

import view.GameWindow;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {

    public final static Map<Integer, Map<Integer, Points>> mapPellet = new HashMap<>();
    public static List<Ghost> ghosts;

    public GameMap() {
        setGhosts();
    }

    public void setPellets() {

        for (int i = 0; i < 25; i++) {
            Map<Integer, Points> yPellet = new HashMap<>();
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
    public void setGhosts() {
        ghosts = new java.util.ArrayList<Ghost>();
        ghosts.add(new Blinky("blinky", new Point(9* GameWindow.TILE_SIZE,14* GameWindow.TILE_SIZE )));
        ghosts.add(new Pinky("pinky", new Point(11* GameWindow.TILE_SIZE,14* GameWindow.TILE_SIZE )));
        ghosts.add(new Inky("inky", new Point(13* GameWindow.TILE_SIZE,14* GameWindow.TILE_SIZE )));
        ghosts.add(new Clyde("clyde", new Point(15* GameWindow.TILE_SIZE,14* GameWindow.TILE_SIZE )));
    }

}
