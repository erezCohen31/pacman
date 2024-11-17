package model;

import control.InputHandler;
import control.ShortestPath;
import view.GameWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GhostManager {
    GameWindow gameWindow = GameWindow.getInstance();
    public HashMap<String, Ghost> ghosts = new HashMap<>();
    public PacMan pacMan;
    ShortestPath shortestPath = new ShortestPath();
    private static GhostManager instance;
    BufferedImage imageB;
    BufferedImage imageP;
    BufferedImage imageI;
    BufferedImage imageC;

    Ghost blinky;


    public static GhostManager getInstance() {
        if (instance == null) {
            instance = new GhostManager(PacMan.getInstance(GameWindow.getInstance(), InputHandler.getInstance()), GameWindow.getInstance());
        }
        return instance;
    }

    public void setImage() throws IOException {
        imageB = ImageIO.read(getClass().getResourceAsStream("/ghosts/blinky.png"));
        imageC = ImageIO.read(getClass().getResourceAsStream("/ghosts/clyde.png"));
        imageI = ImageIO.read(getClass().getResourceAsStream("/ghosts/inky.png"));
        imageP = ImageIO.read(getClass().getResourceAsStream("/ghosts/pinky.png"));
    }

    public GhostManager(PacMan pacMan, GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.pacMan = pacMan;
        try {
            setImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ghosts.put("blinky", new Ghost("blinky", 9 * gameWindow.getTileSize(), 14 * gameWindow.getTileSize(), imageB));
        ghosts.put("pinky", new Ghost("pinky", 11 * gameWindow.getTileSize(), 14 * gameWindow.getTileSize(), imageP));
        ghosts.put("inky", new Ghost("inky", 13 * gameWindow.getTileSize(), 14 * gameWindow.getTileSize(), imageI));
        ghosts.put("clyde", new Ghost("clyde", 15 * gameWindow.getTileSize(), 14 * gameWindow.getTileSize(), imageC));

    }


    public void blinkyMove() {
        blinky = ghosts.get("blinky");
        List<int[]> path = shortestPath.findShortestPath(TileManager.mapTileNum, blinky.getPositionX() / gameWindow.getTileSize(), blinky.getPositionY() / gameWindow.getTileSize(), pacMan.getPositionX() / gameWindow.getTileSize(), pacMan.getPositionY() / gameWindow.getTileSize()); // Exemple départ et arrivée
        if (path == null) {
            System.out.println("Aucun chemin trouvé !");
        } else {
            System.out.println("Chemin trouvé :");
        }


    }


}
