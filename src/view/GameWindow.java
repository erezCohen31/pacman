package view;

import control.GameController;
import control.InputHandler;
import model.FruitManger;
import model.PacMan;
import model.TileManager;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {
    public static final int TILE_SIZE = 24;
    public static final int COL = 25;
    public static final int ROW = 31;
    private final Renderer renderer = new Renderer();
    private static GameWindow instance;
    public TileManager tileManager= new TileManager(this);





    private GameWindow() {
        this.setPreferredSize(new Dimension(TILE_SIZE * COL, TILE_SIZE * ROW));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(InputHandler.getInstance());
        this.setFocusable(true);
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
        g2.drawString("SCORE: " + PacMan.score, TILE_SIZE*5,TILE_SIZE*3 );

        // Dessiner High Score
        g2.drawString("HIGH SCORE: " + PacMan.highScore, TILE_SIZE*15,TILE_SIZE*3 );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        renderer.drawMap(g2,this);
        renderer.drawEntity(PacMan.getInstance(this, InputHandler.getInstance()), this, g2);
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
