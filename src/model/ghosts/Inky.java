package model.ghosts;

import model.Ghost;
import view.GameWindow;

public class Inky extends Ghost {
    public Inky(String name, int posX, int posY) {
        super(name, posX, posY);
    }

    @Override
    public void chaseMode() {
        int posX;
        int posY;
        if (pos.x - pacMan.getPositionX() > 0) {
            posX = pos.x - 2 * GameWindow.TILE_SIZE;
        } else if (pos.x - pacMan.getPositionX() == 0) {
            posX = pos.x;
        } else {
            posX = pos.x + 2 * GameWindow.TILE_SIZE;
        }
        if (pos.y - pacMan.getPositionY() > 0) {
            posY = pos.y - 2 * GameWindow.TILE_SIZE;
        } else if (pos.y - pacMan.getPositionY() == 0) {
            posY = pos.y;
        } else {
            posY = pos.y + 2 * GameWindow.TILE_SIZE;
        }

        target.setLocation(posX, posY);

    }


    @Override
    public void scatterMode() {

        target.setLocation(0, 28 * GameWindow.TILE_SIZE);
    }
}
