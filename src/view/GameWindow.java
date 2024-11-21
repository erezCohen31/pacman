package view;

import control.GameController;
import control.InputHandler;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameWindow extends JPanel {
    public static final int TILE_SIZE = 24;
    public static final int COL = 25;
    public static final int ROW = 31;
    private final Renderer renderer = new Renderer();
    private static GameWindow instance;
    public TileManager tileManager;




    public GameWindow() {
        this.setPreferredSize(new Dimension(TILE_SIZE * COL, TILE_SIZE * ROW));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(InputHandler.getInstance());
        this.setFocusable(true);
        tileManager =new TileManager(this);


    }

    public static GameWindow getInstance() {
        if (instance == null) {
            instance = new GameWindow();
        }
        return instance;
    }

    public void drawScore(Graphics2D g2) {
        // Configuration du style de texte
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));

        // Dessiner Score
        g2.drawString("SCORE: " + PacMan.score, TILE_SIZE * 5, TILE_SIZE * 3);

        // Dessiner High Score
        g2.drawString("HIGH SCORE: " + PacMan.highScore, TILE_SIZE * 15, TILE_SIZE * 3);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        renderer.drawMap(g2, this);
    
        PacMan pacManInstance = PacMan.getInstance(GameWindow.getInstance(), InputHandler.getInstance());
        renderer.drawEntity(pacManInstance, this, g2);
        for (int i = 0; i < GameMap.ghosts.size(); i++) {
            renderer.drawGhost(GameMap.ghosts.get(i), this, g2);
        }
        renderer.drawHearth(g2,this);
        renderer.drawEatenFruit(g2,this);
    

    
        renderer.drawPellet(g2);
        drawScore(g2);
        g2.dispose();
    }

    public int getCOL() {
        return COL;
    }

    public int getROW() {
        return ROW;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }
}
