package control;

import model.GameEntity;
import model.TileManager;
import view.GameWindow;

public class CollisionChecker {
    GameWindow gw;

    public CollisionChecker() {
        this.gw = GameWindow.getInstance();
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
                entityLeftCol = (entityLeftWorldX + entity.speed-5) / gw.getTileSize();
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

//    public int CheckObject(GameEntity entity, boolean player) {
//        int index = 999;
//        for (int i = 0; i < gp.obj.length; i++) {
//            if (gp.obj[i] != null) {
//                entity.solidArea.x = entity.positionX + entity.solidArea.x;
//                entity.solidArea.y = entity.positionY + entity.solidArea.y;
//
//                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
//                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
//
//                switch (entity.direction) {
//                    case "up":
//                        entity.solidArea.y -= entity.speed;
//                        if (entity.solidArea.intersects(gp.obj[i].solidArea) ) {
//                            if (gp.obj[i].collision) {
//                                entity.collisionOn=true;
//                            }
//                            if (player) {
//                                index=i;
//                            }
//                        }
//                        break;
//                    case "down":
//                        entity.solidArea.y += entity.speed;
//                        if (entity.solidArea.intersects(gp.obj[i].solidArea) ) {
//                            if (gp.obj[i].collision) {
//                                entity.collisionOn=true;
//                            }
//                            if (player) {
//                                index=i;
//                            }
//                        }
//                        break;
//                    case "left":
//                        entity.solidArea.x -= entity.speed;
//                        if (entity.solidArea.intersects(gp.obj[i].solidArea) ) {
//                            if (gp.obj[i].collision) {
//                                entity.collisionOn=true;
//                            }
//                            if (player) {
//                                index=i;
//                            }
//                        }
//                        break;
//                    case "right":
//                        entity.solidArea.x += entity.speed ;
//                        if (entity.solidArea.intersects(gp.obj[i].solidArea) ) {
//                            if (gp.obj[i].collision) {
//                                entity.collisionOn=true;
//                            }
//                            if (player) {
//                                index=i;
//                            }
//                        }
//                        break;
//                }
//                entity.solidArea.x =entity.solidAreaDefaultX;
//                entity.solidArea.y=entity.solidAreaDefaultY;
//                gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
//                gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY;
//            }
//        }
//        return index;
//    }
}
