package model;

import view.GameWindow;

import java.awt.*;

public class Pinky extends Ghost {
    public Pinky(String name, Point basePos) {
        super(name, basePos);
    }

    @Override
    public void chaseMode() {
        if (pacMan.direction == Direction.LEFT) {
            target.setLocation(pacMan.getPositionX() + 4 * GameWindow.TILE_SIZE, pacMan.getPositionY());
        } else if (pacMan.direction == Direction.RIGHT) {
            target.setLocation(pacMan.getPositionX() - 4 * GameWindow.TILE_SIZE, pacMan.getPositionY());
        } else if (pacMan.direction == Direction.UP) {
            target.setLocation(pacMan.getPositionX(), pacMan.getPositionY() + 4 * GameWindow.TILE_SIZE);
        } else if (pacMan.direction == Direction.DOWN) {
            target.setLocation(pacMan.getPositionX(), pacMan.getPositionY() - 4 * GameWindow.TILE_SIZE);

        }
    }

    @Override
    public void scatterMode() {

            target.setLocation(0, 4 * GameWindow.TILE_SIZE);}



    }

