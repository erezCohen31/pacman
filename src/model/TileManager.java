package model;

import view.GameWindow;
import utils.ImageLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
   private final GameWindow gameWindow;
    static public Tile[] tile;
    static public int[][] mapTileNum;


    public TileManager(GameWindow gw) {
        this.gameWindow = gw;
        tile = new Tile[18];
        mapTileNum = new int[gw.getCOL()][gw.getROW()];
        getTileImage();


        try {
            loadMap("/map/pac_man_panel.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void getTileImage() {
        try {
            ImageLoader loader = ImageLoader.getInstance();
            tile[0] = new Tile(loader.loadImage("/walls/black.png"), true, true);
            tile[1] = new Tile(loader.loadImage("/walls/black.png"), false,false);
            tile[16] = new Tile(loader.loadImage("/walls/black.png"), false,false);
            tile[17] = new Tile(loader.loadImage("/walls/black.png"), false,true);
            tile[2] = new Tile(loader.loadImage("/walls/horizontal.png"), true,true);
            tile[3] = new Tile(loader.loadImage("/walls/vertical.png"), true,true);
            tile[4] = new Tile(loader.loadImage("/walls/db.png"), true,true);
            tile[5] = new Tile(loader.loadImage("/walls/dh.png"), true,true);
            tile[6] = new Tile(loader.loadImage("/walls/gb.png"), true,true);
            tile[7] = new Tile(loader.loadImage("/walls/gh.png"), true,true);

            tile[8] = new Tile(loader.loadImage("/walls/pd.png"), true,true);
            tile[9] = new Tile(loader.loadImage("/walls/pg.png"), true,true);

            tile[10] = new Tile(loader.loadImage("/walls/ph.png"), true,true);
            tile[11] = new Tile(loader.loadImage("/walls/pb.png"), true,true);
            tile[12] = new Tile(loader.loadImage("/walls/cd.png"), true,true);
            tile[13] = new Tile(loader.loadImage("/walls/cg.png"), true,true);

            tile[14] = new Tile(loader.loadImage("/walls/ch.png"), true,true);
            tile[15] = new Tile(loader.loadImage("/walls/cb.png"), true,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) throws IOException {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gameWindow.getCOL() && row < gameWindow.getROW()) {

                String line = br.readLine();
                String[] numbers = line.split(" ");
                while (col < gameWindow.getCOL()) {
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                    col++;
                }

                    col = 0;
                    row++;

            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}