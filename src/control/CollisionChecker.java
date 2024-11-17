package control;

import model.*;
import model.Point;
import view.GameWindow;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class CollisionChecker {
    GameWindow gw;

    public CollisionChecker(GameWindow gameWindow) {
        this.gw = gameWindow;
    }

    public void checkTile(GameEntity entity) {
        int entityLeftWorldX = entity.getPositionX() + entity.solidArea.x;
        int entityRightWorldX = entity.getPositionX() + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.getPositionY() + entity.solidArea.y;
        int entityBottomtWorldY = entity.getPositionY() + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gw.getTileSize();
        int entityRightCol = entityRightWorldX / gw.getTileSize();
        int entityTopRow = entityTopWorldY / gw.getTileSize();
        int entityBottomRow = entityBottomtWorldY / gw.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gw.getTileSize();
                tileNum1 = TileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = TileManager.mapTileNum[entityRightCol][entityTopRow];
                if (TileManager.tile[tileNum1].collision || TileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomtWorldY + entity.speed) / gw.getTileSize();
                tileNum1 = TileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = TileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (TileManager.tile[tileNum1].collision || TileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX + entity.speed - 5) / gw.getTileSize();
                tileNum1 = TileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = TileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (TileManager.tile[tileNum1].collision || TileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gw.getTileSize();
                tileNum1 = TileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = TileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (TileManager.tile[tileNum1].collision || TileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }


    public int checkObject(PacMan pacMan) {


        Map<Integer, Map<Integer, Point>> pellets = GameMap.mapPellet;
        Rectangle solidSpeed = pacMan.solidArea;
        int entityLeftWorldX = pacMan.getPositionX() + pacMan.solidArea.x;
        int entityRightWorldX = pacMan.getPositionX() + pacMan.solidArea.x + pacMan.solidArea.width;
        int entityTopWorldY = pacMan.getPositionY() + pacMan.solidArea.y;
        int entityBottomtWorldY = pacMan.getPositionY() + pacMan.solidArea.y + pacMan.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gw.getTileSize();
        int entityRightCol = entityRightWorldX / gw.getTileSize();
        int entityTopRow = entityTopWorldY / gw.getTileSize();
        int entityBottomRow = entityBottomtWorldY / gw.getTileSize();
        int point = 0;

        switch (pacMan.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - pacMan.speed) / gw.getTileSize();
                return Math.max(checkPelletCollision(entityLeftCol, entityTopRow, solidSpeed, pacMan, pellets), checkPelletCollision(entityRightCol, entityTopRow, solidSpeed, pacMan, pellets));

            case "down":
                entityBottomRow = (entityBottomtWorldY + pacMan.speed) / gw.getTileSize();
                return Math.max(checkPelletCollision(entityLeftCol, entityBottomRow, solidSpeed, pacMan, pellets), checkPelletCollision(entityRightCol, entityBottomRow, solidSpeed, pacMan, pellets));

            case "left":
                entityLeftCol = (entityLeftWorldX + pacMan.speed - 5) / gw.getTileSize();
                return Math.max(checkPelletCollision(entityLeftCol, entityTopRow, solidSpeed, pacMan, pellets), checkPelletCollision(entityLeftCol, entityBottomRow, solidSpeed, pacMan, pellets));

            case "right":
                entityRightCol = (entityRightWorldX + pacMan.speed) / gw.getTileSize();
                return Math.max(checkPelletCollision(entityRightCol, entityTopRow, solidSpeed, pacMan, pellets), checkPelletCollision(entityRightCol, entityBottomRow, solidSpeed, pacMan, pellets));

        }
        return point;
    }

    private int checkPelletCollision(int col, int row, Rectangle solidSpeed, PacMan pacMan, Map<Integer, Map<Integer, Point>> pellets) {
        int point = 0;
        if (pellets.containsKey(col) && pellets.get(col).containsKey(row) && pellets.get(col).get(row) != null && solidSpeed.intersects(pellets.get(col).get(row).solidArea)) {
            pacMan.collisionOn = true;
            point = pellets.get(col).get(row).getPoints();
            pellets.get(col).put(row, null);
            return point;
        }
        return point;
    }


}
