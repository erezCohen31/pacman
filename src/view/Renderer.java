package view;

import control.GameController;
import control.InputHandler;
import model.*;
import model.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class  Renderer {

    public void drawEntity(GameEntity entity, GameWindow gw, Graphics2D g2) {
        BufferedImage image = null;
        PacMan pacMan = PacMan.getInstance(GameWindow.getInstance(), InputHandler.getInstance());
        switch (entity.direction) {
            case "up":
                if (pacMan.spriteNum == 1) {
                    image = pacMan.up1;
                }
                if (pacMan.spriteNum == 2) {
                    image = pacMan.up2;
                }
                if (pacMan.spriteNum == 3) {
                    image = pacMan.up3;
                }
                break;
            case "down":
                if (pacMan.spriteNum == 1) {
                    image = pacMan.down1;
                }
                if (pacMan.spriteNum == 2) {
                    image = pacMan.down2;
                }
                if (pacMan.spriteNum == 3) {
                    image = pacMan.down3;
                }
                break;
            case "left":
                if (pacMan.spriteNum == 1) {
                    image = pacMan.left1;
                }
                if (pacMan.spriteNum == 2) {
                    image = pacMan.left2;
                }
                if (pacMan.spriteNum == 3) {
                    image = pacMan.left3;
                }
                break;
            case "right":
                if (pacMan.spriteNum == 1) {
                    image = pacMan.right1;
                }
                if (pacMan.spriteNum == 2) {
                    image = pacMan.right2;
                }
                if (pacMan.spriteNum == 3) {
                    image = pacMan.right3;
                }
                break;
        }
        g2.drawImage(image, entity.getPositionX(), entity.getPositionY(), (int) (gw.getTileSize()), (int) (gw.getTileSize()/1.3), null);
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
    public void drawPellet(Graphics2D g2){
        for (Map.Entry<Integer, Map<Integer, Point>> entry : GameMap.mapPellet.entrySet()) {
            Map<Integer, Point> yPellet = entry.getValue();
            if (yPellet != null) {
                for (Map.Entry<Integer, Point> subEntry : yPellet.entrySet()) {
                    Point pellet = subEntry.getValue();
                    if (pellet != null && pellet.image != null) {
                        g2.drawImage(pellet.image, pellet.getPositionX() * GameWindow.TILE_SIZE, pellet.getPositionY() * GameWindow.TILE_SIZE, GameWindow.TILE_SIZE, GameWindow.TILE_SIZE, null);
                    }
                }
            }
        }
    }
    public void drawGhost(Graphics2D g2d, Ghost ghost) {
        // Récupérer les coordonnées du fantôme
        int x = ghost.getPositionX();
        int y = ghost.getPositionY();

        // Si le fantôme est dans un état spécial (ex : mode "peur"), ajuster le rendu
        if (ghost.isFear) {
            // Par exemple, changer la couleur pour un effet spécial de peur
            g2d.setColor(Color.BLUE);  // Vous pouvez ajuster la couleur ou appliquer un effet
        }


        // Dessiner l'image du fantôme à la position donnée
        g2d.drawImage(ghost.image, x, y, GameWindow.TILE_SIZE, GameWindow.TILE_SIZE, null);
    }
}
