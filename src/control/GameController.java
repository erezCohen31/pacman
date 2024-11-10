package control;

import model.*;
import view.GameWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {
    private final PacMan pacMan = PacMan.getInstance(GameWindow.getInstance(), InputHandler.getInstance());
    private final Ghost[] ghosts = {new Ghost(), new Ghost(), new Ghost(), new Ghost()};
    //    public final static List<Pellet> pellets =new ArrayList<>();
    public final static Map<Integer, Map<Integer, Point>> mapPellet = new HashMap<>();


    private final GameMap map = new GameMap();
    private final int FPS = 60;

    public void startGame() {
        System.out.println("Game started");
    }

    public void updateGame() {
        pacMan.move();
    }

    public boolean isGameOver() {
        return pacMan.getLives() <= 0;
    }

    public int getFPS() {
        return FPS;
    }

    public void setPellets() {

        for (int i = 0; i < 25; i++) {
            Map<Integer, Point> yPellet = new HashMap<>();
            for (int j = 5; j < 28; j++) {
                if (TileManager.mapTileNum[i][j] == 1) {
//

                    yPellet.put(j, new Pellet(i, j, 10));

                    yPellet.get(j).setSolidArea();

//

                } else if (TileManager.mapTileNum[i][j] == 16) {
//

                    yPellet.put(j, new BigPellet(i, j, 50));

                    yPellet.get(j).setSolidArea();

//


                }

            }
            mapPellet.put(i, yPellet);
        }

    }
}
