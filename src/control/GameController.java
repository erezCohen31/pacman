package control;

import model.*;
import view.GameWindow;

import java.util.*;

public class GameController {
    private final PacMan pacMan = PacMan.getInstance(GameWindow.getInstance(), InputHandler.getInstance());
    public FruitManager fruitManager = new FruitManager();
    public GameMap gameMap = new GameMap();
    GhostManager ghostManager=GhostManager.getInstance();


    private final GameMap map = new GameMap();
    private final int FPS = 60;

    public void startGame() {
        System.out.println("Game started");
        gameMap.setPellets();
        fruitManager.startFruit();
    }

    public void updateGame() {

        pacMan.move();

        fruitManager.appearFruit();
        ghostManager.blinkyMove();

    }

    public boolean isGameOver() {
        return pacMan.getLives() <= 0;
    }

    public int getFPS() {
        return FPS;
    }


}
