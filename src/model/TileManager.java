package model;


import view.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
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
        tile = new Tile[10];
        mapTileNum = new int[gw.getCOL()][gw.getROW()];
        getTileImage();

        try {
            loadMap("/model/pac_man_pannel.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void getTileImage() {
        try {
            tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/model/black.png")), false);

            tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/model/blue.png")), true);




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) throws IOException {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 6;
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