package control;

import model.*;
import model.Points;
import view.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollisionChecker {
    GameWindow gw;

    public CollisionChecker(GameWindow gameWindow) {
        this.gw = gameWindow;
    }

    public boolean checkTile(GameEntity entity) {
        int entityLeftWorldX = entity.getPositionX() + entity.solidArea.x;
        int entityRightWorldX = entity.getPositionX() + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.getPositionY() + entity.solidArea.y;
        int entityBottomWorldY = entity.getPositionY() + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gw.getTileSize();
        int entityRightCol = entityRightWorldX / gw.getTileSize();
        int entityTopRow = entityTopWorldY / gw.getTileSize();
        int entityBottomRow = entityBottomWorldY / gw.getTileSize();

        int tileNum1, tileNum2;

        if (entity instanceof PacMan) {
            PacMan pacMan = (PacMan) entity;
            switch (pacMan.direction) {
                case UP:
                    entityTopRow = (entityTopWorldY - entity.speed) / gw.getTileSize();
                    tileNum1 = TileManager.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = TileManager.mapTileNum[entityRightCol][entityTopRow];
                    return TileManager.tile[tileNum1].collision || TileManager.tile[tileNum2].collision;
                case DOWN:
                    entityBottomRow = (entityBottomWorldY + entity.speed) / gw.getTileSize();
                    tileNum1 = TileManager.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = TileManager.mapTileNum[entityRightCol][entityBottomRow];
                    return TileManager.tile[tileNum1].collision || TileManager.tile[tileNum2].collision;
                case LEFT:
                    final int LEFT_OFFSET = 5;
                    entityLeftCol = (entityLeftWorldX + entity.speed - LEFT_OFFSET) / gw.getTileSize();
                    tileNum1 = TileManager.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = TileManager.mapTileNum[entityLeftCol][entityBottomRow];
                    return TileManager.tile[tileNum1].collision || TileManager.tile[tileNum2].collision;
                case RIGHT:
                    entityRightCol = (entityRightWorldX + entity.speed) / gw.getTileSize();
                    tileNum1 = TileManager.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = TileManager.mapTileNum[entityRightCol][entityBottomRow];
                    return TileManager.tile[tileNum1].collision || TileManager.tile[tileNum2].collision;
            }
        }
        return false;
    }

    public int checkObject(PacMan pacMan) {
        Map<Integer, Map<Integer, Points>> pellets = GameMap.mapPellet;
        Rectangle solidSpeed = pacMan.solidArea;
        int entityLeftWorldX = pacMan.getPositionX() + pacMan.solidArea.x;
        int entityRightWorldX = pacMan.getPositionX() + pacMan.solidArea.x + pacMan.solidArea.width;
        int entityTopWorldY = pacMan.getPositionY() + pacMan.solidArea.y;
        int entityBottomWorldY = pacMan.getPositionY() + pacMan.solidArea.y + pacMan.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gw.getTileSize();
        int entityRightCol = entityRightWorldX / gw.getTileSize();
        int entityTopRow = entityTopWorldY / gw.getTileSize();
        int entityBottomRow = entityBottomWorldY / gw.getTileSize();
        int point = 0;

        switch (pacMan.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - pacMan.speed) / gw.getTileSize();
                return Math.max(checkPelletCollision(entityLeftCol, entityTopRow, solidSpeed, pacMan, pellets), checkPelletCollision(entityRightCol, entityTopRow, solidSpeed, pacMan, pellets));
            case DOWN:
                entityBottomRow = (entityBottomWorldY + pacMan.speed) / gw.getTileSize();
                return Math.max(checkPelletCollision(entityLeftCol, entityBottomRow, solidSpeed, pacMan, pellets), checkPelletCollision(entityRightCol, entityBottomRow, solidSpeed, pacMan, pellets));
            case LEFT:
                final int LEFT_OFFSET = 5;
                entityLeftCol = (entityLeftWorldX + pacMan.speed - LEFT_OFFSET) / gw.getTileSize();
                return Math.max(checkPelletCollision(entityLeftCol, entityTopRow, solidSpeed, pacMan, pellets), checkPelletCollision(entityLeftCol, entityBottomRow, solidSpeed, pacMan, pellets));
            case RIGHT:
                entityRightCol = (entityRightWorldX + pacMan.speed) / gw.getTileSize();
                return Math.max(checkPelletCollision(entityRightCol, entityTopRow, solidSpeed, pacMan, pellets), checkPelletCollision(entityRightCol, entityBottomRow, solidSpeed, pacMan, pellets));
        }
        return point;
    }

    private int checkPelletCollision(int col, int row, Rectangle solidSpeed, PacMan pacMan, Map<Integer, Map<Integer, Points>> pellets) {
        int point = 0;
        if (pellets.containsKey(col) && pellets.get(col).containsKey(row) && pellets.get(col).get(row) != null && solidSpeed.intersects(pellets.get(col).get(row).solidArea)) {
            pacMan.collisionOn = true;
            point = pellets.get(col).get(row).getPoints();

            // Vérifie si c'est un gros pellet (50 points) ou un fruit
            if (point >= 50 || pellets.get(col).get(row) instanceof BigPellet) {
                // Active le mode escape pour tous les fantômes
                for (Ghost ghost : GameMap.ghosts) {
                    ghost.startEscapeMode();
                }
            }

            pellets.get(col).put(row, null);
            return point;
        }
        return point;
    }

    public boolean checkGhostCollision(Ghost ghost, PacMan pacMan) {
        Rectangle ghostSolidArea = new Rectangle(
                ghost.getPositionX() + ghost.solidArea.x,
                ghost.getPositionY() + ghost.solidArea.y,
                ghost.solidArea.width,
                ghost.solidArea.height
        );

        Rectangle pacManSolidArea = new Rectangle(
                pacMan.getPositionX() + pacMan.solidArea.x,
                pacMan.getPositionY() + pacMan.solidArea.y,
                pacMan.solidArea.width,
                pacMan.solidArea.height
        );

        return ghostSolidArea.intersects(pacManSolidArea);
    }

    public List<String> getDirection(Ghost ghost) {

        List<String> direction = new ArrayList<>();
        String currentDirection = ghost.getCurrentDirection();
        int nextTile;

        if (!currentDirection.equals("DOWN")) {
            nextTile = TileManager.mapTileNum[ghost.getPos().x / GameWindow.TILE_SIZE][(ghost.getPos().y - 1) / GameWindow.TILE_SIZE];
            if (nextTile == 1) {
                direction.add("UP");
            }
        }


        if (!currentDirection.equals("LEFT")) {
            nextTile = TileManager.mapTileNum[(ghost.getPos().x+24 + 1) / GameWindow.TILE_SIZE][ghost.getPos().y / GameWindow.TILE_SIZE];
            if (nextTile == 1) {
                direction.add("RIGHT");
            }
        }


        if (!currentDirection.equals("UP")) {
            nextTile = TileManager.mapTileNum[ghost.getPos().x / GameWindow.TILE_SIZE][(ghost.getPos().y +24+ 1) / GameWindow.TILE_SIZE];
            if (nextTile == 1) {
                direction.add("DOWN");
            }
        }


       if (!currentDirection.equals("RIGHT")) {
            nextTile = TileManager.mapTileNum[(ghost.getPos().x - 1) / GameWindow.TILE_SIZE][ghost.getPos().y / GameWindow.TILE_SIZE];
            if (nextTile == 1) {
                direction.add("LEFT");
            }
        }
        if (direction.isEmpty()) {
            switch (currentDirection) {
                case "UP" -> direction.add("DOWN");
                case "DOWN" -> direction.add("UP");
                case "LEFT" -> direction.add("RIGHT");
                case "RIGHT" -> direction.add("LEFT");
            }
        }

        return direction;
    }
}
