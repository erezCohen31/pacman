package model.ghosts;

import model.Ghost;
import view.GameWindow;

public class Blinky extends Ghost {
    public Blinky(String name, int posX, int posY) {
        super(name, posX, posY);
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

