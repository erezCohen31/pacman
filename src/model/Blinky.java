package model;

import view.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blinky extends Ghost {
    public Blinky(String name, Point basePos) {
        super(name, basePos);
    }

    @Override
    public void chaseMode() {
        target.setLocation(pacMan.pos);

    }

    @Override
    public void scatterMode() {

            target.setLocation(24 * GameWindow.TILE_SIZE, 4 * GameWindow.TILE_SIZE);

    }
}

