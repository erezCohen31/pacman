package view;

import model.*;
import model.Points;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Renderer {

    public void drawEntity(GameEntity entity, GameWindow gw, Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage[] images = entity.directionImages.get(entity.direction);
        if (images != null && entity.spriteNum > 0 && entity.spriteNum <= images.length) {
            image = images[entity.spriteNum - 1];
        }
        if (image != null) {
            g2.drawImage(image, entity.getPositionX(), entity.getPositionY(), (int) (gw.getTileSize()), (int) (gw.getTileSize() / 1.3), null);
        }
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

    public void drawPellet(Graphics2D g2) {
        for (Map.Entry<Integer, Map<Integer, Points>> entry : GameMap.mapPellet.entrySet()) {
            Map<Integer, Points> yPellet = entry.getValue();
            if (yPellet != null) {
                for (Map.Entry<Integer, Points> subEntry : yPellet.entrySet()) {
                    Points pellet = subEntry.getValue();
                    if (pellet != null && pellet.image != null) {
                        g2.drawImage(pellet.image, pellet.getPositionX() * GameWindow.TILE_SIZE, pellet.getPositionY() * GameWindow.TILE_SIZE, GameWindow.TILE_SIZE, GameWindow.TILE_SIZE, null);
                    }
                }
            }
        }
    }

    public void drawGhost(Ghost ghost, GameWindow gw, Graphics2D g2) {
        BufferedImage image = null;
        if (ghost.escapeMode && !ghost.isEaten) {
            image = ghost.afraidImage;
        } else if (ghost.isEaten) {
            image = ghost.eyeImage;
        } else {
            image = ghost.directionImage.get(ghost.direction);
        }

        if (image != null) {
            g2.drawImage(image, ghost.getPositionX(), ghost.getPositionY(), (int) (gw.getTileSize()), (int) (gw.getTileSize() / 1.3), null);
        }
    }

    public void drawHearth(Graphics2D g2, GameWindow gw) {
        for (int i = 0; i < GameMap.listHearth.size(); i++) {
            g2.drawImage(GameMap.hearthImage, GameMap.listHearth.get(i).x * gw.getTileSize(), GameMap.listHearth.get(i).y * gw.getTileSize(), (int) (gw.getTileSize() * 1.5), (int) (gw.getTileSize() * 1.5), null);
        }

    }

    public void drawEatenFruit(Graphics2D g2, GameWindow gw) {
        int posX = 10, posY = 29;
        for (int i = 0; i < GameMap.eatenFruits.size(); i++) {
            g2.drawImage(GameMap.eatenFruits.get(i).image, posX * gw.getTileSize(), posY * gw.getTileSize(), gw.getTileSize(), gw.getTileSize(), null);
            posX++;

        }
    }

}
