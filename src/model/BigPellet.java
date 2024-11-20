package model;

import view.GameWindow;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BigPellet extends Points {

    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;



    public BigPellet(int x,int y,int points) {
        this.points = points;
        setPositionX(x);
        setPositionY(y);
        loadBigPelletImage();
        collisionOn=true;


    }
    public void setSolidArea(){
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width= GameWindow.TILE_SIZE;
        solidArea.height= GameWindow.TILE_SIZE;

    }



    private void loadBigPelletImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/pellets/bigpellet.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
