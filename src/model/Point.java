package model;

import view.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Point extends GameEntity {
    int points;


    String name;
    int col;
    int row;


    @Override
    public int eaten() {
        return 0;
    }

    public void setSolidArea() {
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = GameWindow.TILE_SIZE;
        solidArea.height = GameWindow.TILE_SIZE;

    }


    public int getPoints() {
        return points;
    }
}
