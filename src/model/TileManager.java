package model;


import view.GameWindow;

import javax.imageio.ImageIO;
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
        tile = new Tile[16];
        mapTileNum = new int[gw.getCOL()][gw.getROW()];
        getTileImage();

        try {
            loadMap("/model/pac_man_panel.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void getTileImage() {
        try {
            tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/blue.png")), false);

            tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/black.png")), false);

            tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/horizontal.png")), true);

            tile[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/vertical.png")), true);
            tile[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/db.png")), true);
            tile[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/dh.png")), true);
            tile[6] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/gb.png")), true);
            tile[7] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/gh.png")), true);

            tile[8] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/pd.png")), true);
            tile[9] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/pg.png")), true);

            tile[10] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/ph.png")), true);
            tile[11] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/pb.png")), true);
            tile[12] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/cd.png")), true);
            tile[13] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/cg.png")), true);

            tile[14] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/ch.png")), true);
            tile[15] = new Tile(ImageIO.read(getClass().getResourceAsStream("/walls/cb.png")), true);







        } catch (IOException e) {
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