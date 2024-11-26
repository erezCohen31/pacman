package model.ghosts;

import model.Ghost;
import view.GameWindow;

public class Clyde extends Ghost {
    public Clyde(String name, int posX, int posY) {
        super(name, posX, posY);
    }

    @Override
    public void chaseMode() {
        if (pos.distance(pacMan.pos) <= 192) {
            target.setLocation(BASE_POS);
        } else {
            target.setLocation(pacMan.pos);
        }
    }

    @Override
    public void scatterMode() {

        target.setLocation(24 * GameWindow.TILE_SIZE, 28 * GameWindow.TILE_SIZE);
    }
}

