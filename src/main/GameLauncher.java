// package main;
package main;

import control.GameLoop;
import view.GameWindow;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        JFrame window = createWindow();
        GameWindow gameWindow = new GameWindow();
        GameLoop gameLoop = new GameLoop(gameWindow);

        window.add(gameWindow);
        window.pack();
        window.setVisible(true);
        gameLoop.startGameThread();
    }

    private static JFrame createWindow() {
        JFrame window = new JFrame("Pac Man");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        return window;
    }
}
