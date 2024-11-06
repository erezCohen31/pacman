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
    private final Renderer renderer = new Renderer();
    private static GameWindow instance;
    TileManager tileManager = new TileManager(this);


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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        renderer.drawMap(g2, this);
        renderer.drawEntity(PacMan.getInstance(this, InputHandler.getInstance()), this, g2);
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
