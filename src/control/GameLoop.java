// package control;
package control;

import view.GameWindow;

public class GameLoop implements Runnable {
    private final GameController gameController;
    private final GameWindow window;
    private Thread gameThread;

    public GameLoop(GameWindow window) {
        this.gameController = new GameController();
        this.window = window;
    }

    public void startGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();

        }
    }

    @Override
    public void run() {
        gameController.setPellets();
        final double drawInterval = 1e9 / gameController.getFPS();
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                gameController.updateGame();
                window.repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1e9) {
                System.out.println("FPS:" + drawCount);

                drawCount = 0;
                timer = 0;
            }
        }
    }
}
