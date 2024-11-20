package model;

import view.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Clyde extends Ghost {
    public Clyde(String name, Point basePos) {
        super(name, basePos);
    }

    @Override
    public void chaseMode() {

    }

    @Override
    public void scatterMode() {

            target.setLocation(24 * GameWindow.TILE_SIZE, 28 * GameWindow.TILE_SIZE);
        }
    }

