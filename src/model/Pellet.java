package model;


import utils.ImageLoader;

public class Pellet extends Points {
    public enum PelletType {
        SMALL,
        BIG
    }

    private final PelletType type;



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

    private void loadPelletImage() {
        String imagePath = type == PelletType.BIG ? "/pellets/bigpellet.png" : "/pellets/pellet.png";
        image = ImageLoader.getInstance().loadImage(imagePath);
    }


}
