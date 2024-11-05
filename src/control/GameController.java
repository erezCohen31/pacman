package control;

import model.GameMap;
import model.Ghost;
import model.PacMan;
import view.GameWindow;

public class GameController {
    private PacMan pacMan;
    private Ghost blinky;
    private Ghost pinky;
    private Ghost inky;
    private Ghost clyde;
    private GameMap map;
    private final int FPS = 60;

    public GameController() {
        initializeGameEntities();
    }

    private void initializeGameEntities() {
        this.blinky = new Ghost();
        this.pinky = new Ghost();
        this.inky = new Ghost();
        this.clyde = new Ghost();
        this.map = new GameMap();
    }

    public void startGame() {
        // Initialiser ou réinitialiser le jeu
        System.out.println("Game started");
    }

    public void updateGame() {
        // Logique de mise à jour de l'état du jeu (déplacement, etc.)

    }

    private void checkCollisions() {
        // Vérifier les collisions entre PacMan et les fantômes, ou les murs

    }

    public boolean isGameOver() {
        // Vérifier si le jeu est terminé
        return pacMan.lives <= 0;
    }

    // Getters pour FPS, pacMan, et autres si nécessaire
    public int getFPS() {
        return FPS;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public GameMap getMap() {
        return map;
    }
}
