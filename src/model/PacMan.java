package model;

import control.CollisionChecker;
import control.InputHandler;
import utils.ImageLoader;
import view.GameWindow;
import java.awt.*;
import java.awt.image.BufferedImage;


public class PacMan extends GameEntity {

    private int lives = 3;
    public static int score = 0;
    public static int highScore = 0;
    private final GameWindow gameWindow;
    private final InputHandler inputHandler;





    private static PacMan instancePacMan;
    public CollisionChecker cChecker;

    public PacMan(GameWindow gw, InputHandler keyHandler) {
        this.gameWindow = gw;
        this.inputHandler = keyHandler;
        solidArea = new Rectangle(4, 4, 10, 10);
        cChecker = new CollisionChecker(gw);
        loadPlayerImage();
        setDefaultValues();
    }

    public static PacMan getInstance(GameWindow gw, InputHandler keyHandler) {
        if (instancePacMan == null) {
            instancePacMan = new PacMan(gw, keyHandler);
        }
        return instancePacMan;
    }

    public void setDefaultValues() {
        pos = new Point();
        setPositionX(gameWindow.getTileSize() * 12);
        setPositionY(gameWindow.getTileSize() * 20);
        speed = 2;
        direction = Direction.DOWN;
    }

    public void move() {
        if (inputHandler.upPressed || inputHandler.downPressed || inputHandler.leftPressed || inputHandler.rightPressed) {
            updateDirection();
            collisionOn = false;
            checkCollisions();
            if (!collisionOn) {
                updatePosition();
                updateSprite();
            }
            for (int i = 0; i < GameMap.ghosts.size(); i++) {
                if (GameMap.ghosts.get(i).collisionPacMan && !GameMap.ghosts.get(i).escapeMode) {
                    eaten();


                    GameMap.listHearth.removeLast();
                }
                if (GameMap.ghosts.get(i).collisionPacMan && GameMap.ghosts.get(i).isPoint) {
                    score += GameMap.ghosts.get(i).point;
                    GameMap.ghosts.get(i).isPoint = false;
                }
            }

        }
    }

    private void updateDirection() {
        if (inputHandler.upPressed) {
            direction = Direction.UP;
        } else if (inputHandler.downPressed) {
            direction = Direction.DOWN;
        } else if (inputHandler.leftPressed) {
            direction = Direction.LEFT;
        } else if (inputHandler.rightPressed) {
            direction = Direction.RIGHT;
        }
    }

    private void checkCollisions() {
        collisionOn = cChecker.checkTile(this);
        score += cChecker.checkObject(this);


    }

    private void updatePosition() {
        switch (direction) {
            case UP:
                setPositionY(getPositionY() - speed);
                break;
            case DOWN:
                setPositionY(getPositionY() + speed);
                break;
            case LEFT:
                if (getPositionX() == 8) {
                    setPositionX(598);
                } else {
                    setPositionX(getPositionX() - speed);
                }
                break;
            case RIGHT:
                if (getPositionX() == 582) {
                    setPositionX(0);
                } else {
                    setPositionX(getPositionX() + speed);
                }
                break;
        }
    }

    private void updateSprite() {
        spriteCounter++;
        if (spriteCounter > 5) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    private void loadPlayerImage() {
        ImageLoader loader = ImageLoader.getInstance();
        for (Direction dir : Direction.values()) {
            BufferedImage[] images = new BufferedImage[3];
            for (int i = 0; i < 3; i++) {
                images[i] = loader.loadImage("/pacman/pac_man_" + dir.name().toLowerCase() + "_" + (i + 1) + ".png");
            }
            if (images[0] != null && images[1] != null && images[2] != null) {
                directionImages.put(dir, images);
            }
        }
    }


    public void eaten() {
        lives--;
        setDefaultValues();
    }

    public int getLives() {
        return lives;
    }




}
