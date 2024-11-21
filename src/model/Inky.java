package model;

import view.GameWindow;

import java.awt.*;

public class Inky extends Ghost {
    public Inky(String name,  int posX,int posY ) {
        super(name,posX,posY );
    }

    @Override
    public void chaseMode() {

    }

    @Override
    public void scatterMode() {

        target.setLocation(0, 28 * GameWindow.TILE_SIZE);
    }
}
