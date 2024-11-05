// package view;
package view;

import control.InputHandler;
import model.PacMan;
import model.TileManager;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {
    private static final int TILE_SIZE = 24;
    private static final int COL = 25;
    private static final int ROW = 31;
    private final int width = TILE_SIZE * COL;
    private final int height = TILE_SIZE * ROW;
    private final InputHandler keyHandler = new InputHandler();
    TileManager tileManager =new TileManager(this);
    Renderer renderer = new Renderer();
    public PacMan pacMan = new PacMan(this,keyHandler);

    public GameWindow() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public  int getCOL() {
        return COL;
    }

    public  int getROW() {
        return ROW;
    }

    public  int getTileSize() {
        return TILE_SIZE;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Dessiner les éléments du jeu ici
        renderer.drawMap(g2,this);
        renderer.drawEntity(pacMan,this,g2);
        g2.dispose();
    }
}
