package view;

import model.GameEntity;
import model.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    public void drawEntity(GameEntity entity, GameWindow gw, Graphics2D g2) {
        BufferedImage image = null;
        switch (entity.direction) {
            case "up" -> image = entity.up1;
            case "down" -> image = entity.down1;
            case "left" -> image = entity.left1;
            case "right" -> image = entity.right1;
        }
        g2.drawImage(image, entity.getPositionX(), entity.getPositionY(), gw.getTileSize(), gw.getTileSize(), null);
    }

    public void drawMap(Graphics2D g2, GameWindow gw) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gw.getCOL() && worldRow < gw.getROW()) {
            int tileNum = TileManager.mapTileNum[worldCol][worldRow];
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
