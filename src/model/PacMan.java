package model;

import control.CollisionChecker;
import control.GameController;
import control.InputHandler;
import view.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PacMan extends GameEntity {

    private int lives = 3;
    public static int score = 0;
    public static int highScore = 0;
    private final GameWindow gameWindow;
    private final InputHandler inputHandler;

    private boolean gameOver = false;
    private int startX = 12;
    private int startY = 20;

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
        try {
            for (Direction dir : Direction.values()) {
                BufferedImage[] images = new BufferedImage[3];
                for (int i = 0; i < 3; i++) {
                    images[i] = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_" + dir.name().toLowerCase() + "_" + (i + 1) + ".png"));
                }
                if (images[0] != null && images[1] != null && images[2] != null) {
                    directionImages.put(dir, images);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initCollisionChecker() {
        cChecker = new CollisionChecker(gameWindow);
    }

    public int eaten() {
        lives--;
        setDefaultValues();
        return lives;
    }

    public int getLives() {
        return lives;
    }

    public void die() {
        lives--; // Réduit le nombre de vies
        if (lives > 0) {
            // Réinitialise la position de Pac-Man à sa position de départ
            setDefaultValues();
            collisionOn = false;
        } else {
            // Game Over
            gameOver = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
