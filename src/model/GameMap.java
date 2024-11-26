package model;

import model.ghosts.Blinky;
import model.ghosts.Clyde;
import model.ghosts.Inky;
import model.ghosts.Pinky;
import view.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {

    public final static Map<Integer, Map<Integer, Points>> mapPellet = new HashMap<>();
    public static List<Ghost> ghosts;
    public static BufferedImage hearthImage;
    public static List<Point> listHearth = new ArrayList<>();
    public static List<Points> eatenFruits = new ArrayList<>();

    public GameMap() {
        setGhosts();
        loadPHearthImage();
        setHearthMap();
    }

    public void setHearthMap() {
        listHearth.add(new Point(2,29));
        listHearth.add(new Point(4,29));
        listHearth.add(new Point(6,29));
    }

    public void setPellets() {

        for (int i = 0; i < 25; i++) {
            Map<Integer, Points> yPellet = new HashMap<>();
            for (int j = 5; j < 28; j++) {
                if (TileManager.mapTileNum[i][j] == 1||TileManager.mapTileNum[i][j] == 17) {

                    Pellet smallPellet = new Pellet(i, j, 10, Pellet.PelletType.SMALL);

                    yPellet.put(j, smallPellet);

                    yPellet.get(j).setSolidArea();


                } else if (TileManager.mapTileNum[i][j] == 16) {

                    Pellet bigPellet = new Pellet(i, j, 50, Pellet.PelletType.BIG);

                    yPellet.put(j, bigPellet);

                    yPellet.get(j).setSolidArea();


                }

            }
            mapPellet.put(i, yPellet);
        }

    }
    public void setGhosts() {
        ghosts = new java.util.ArrayList<Ghost>();
        ghosts.add(new Blinky("blinky", 9 * GameWindow.TILE_SIZE,14* GameWindow.TILE_SIZE ));
        ghosts.add(new Pinky("pinky", 11* GameWindow.TILE_SIZE,14* GameWindow.TILE_SIZE ));
        ghosts.add(new Inky("inky", 13* GameWindow.TILE_SIZE,14* GameWindow.TILE_SIZE ));
        ghosts.add(new Clyde("clyde", 15* GameWindow.TILE_SIZE,14* GameWindow.TILE_SIZE ));
    }
    private void loadPHearthImage() {
        try {

            hearthImage = ImageIO.read(getClass().getResourceAsStream("/hearth/coeur.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
