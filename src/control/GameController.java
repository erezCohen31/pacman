package control;

import model.*;
import view.GameWindow;

public class GameController {
    private final PacMan pacMan = PacMan.getInstance(GameWindow.getInstance(), InputHandler.getInstance());
    private final FruitManager fruitManager = new FruitManager();
    private final GameMap gameMap = new GameMap();

    private final int FPS = 60;

    public void startGame() {
        System.out.println("Game started");
        gameMap.setPellets();
        fruitManager.startFruit();

    }

    public void updateGame() {
        pacMan.move();
        fruitManager.appearFruit();
        for (int i = 0; i < GameMap.ghosts.size(); i++) {
            GameMap.ghosts.get(i).move();

        }





    }

    public boolean isGameOver() {
        return pacMan.getLives() == 0;
    }

    public int getFPS() {
        return FPS;
    }

}
