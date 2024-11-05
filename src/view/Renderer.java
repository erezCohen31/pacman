package view;

import model.GameEntity;
import model.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    void drawEntity(GameEntity entity,GameWindow gw,Graphics2D g2){
        BufferedImage image = null;
        switch (entity.direction) {
            case "up":
                if (entity.spriteNum == 1) {
                    image = entity.up1;
                }
                if (entity.spriteNum == 2) {
                    image = entity.up2;
                }
                break;
            case "down":
                if (entity.spriteNum == 1) {
                    image = entity.down1;
                }
                if (entity.spriteNum == 2) {
                    image = entity.down2;
                }
                break;
            case "left":
                if (entity.spriteNum == 1) {
                    image = entity.left1;
                }
                if (entity.spriteNum == 2) {
                    image = entity.left2;
                }
                break;
            case "right":
                if (entity.spriteNum == 1) {
                    image =entity.right1;
                }
                if (entity.spriteNum == 2) {
                    image = entity.right2;
                }
                break;
        }

        g2.drawImage(image, entity.getPositionX(), entity.getPositionY(), gw.getTileSize(), gw.getTileSize(), null);
    }

        public void drawMap(Graphics2D g2, GameWindow gw) {
            int worldCol = 0;
            int worldRow = 6;

            while (worldCol < gw.getCOL() && worldRow < gw.getROW()) {

                int tileNum =TileManager.mapTileNum[worldCol][worldRow];

                int worldX = worldCol * gw.getTileSize();
                int worldY = worldRow * gw.getTileSize();



                    g2.drawImage(TileManager.tile[tileNum].image, worldX, worldY, gw.getTileSize(), gw.getTileSize(), null);



                worldCol++;

                if (worldCol == gw.getCOL()) {
                    worldCol = 0;

                    worldRow++;

                }
            }

        }

}
