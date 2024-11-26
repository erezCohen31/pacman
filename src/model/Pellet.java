package model;

import view.GameWindow;
import utils.ImageLoader;

public class Pellet extends Points {
    public enum PelletType {
        SMALL,
        BIG
    }

    private final PelletType type;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public Pellet(int x, int y, int points, PelletType type) {
        this.points = points;
        this.type = type;
        setPositionX(x);
        setPositionY(y);
        loadPelletImage();
        collisionOn = true;
        if (type == PelletType.BIG) {
            setSolidArea();
        }
    }

    public void setSolidArea() {
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = GameWindow.TILE_SIZE;
        solidArea.height = GameWindow.TILE_SIZE;
    }

    private void loadPelletImage() {
        String imagePath = type == PelletType.BIG ? "/pellets/bigpellet.png" : "/pellets/pellet.png";
        image = ImageLoader.getInstance().loadImage(imagePath);
    }

    public PelletType getType() {
        return type;
    }
}
