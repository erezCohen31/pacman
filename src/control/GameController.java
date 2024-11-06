package control;

import model.GameMap;
import model.Ghost;
import model.PacMan;
import view.GameWindow;

public class GameController {
    private final PacMan pacMan = PacMan.getInstance(GameWindow.getInstance(), InputHandler.getInstance());
    private final Ghost[] ghosts = {new Ghost(), new Ghost(), new Ghost(), new Ghost()};
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
}
