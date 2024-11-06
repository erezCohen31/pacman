package model;

import control.CollisionChecker;
import control.InputHandler;
import view.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PacMan extends GameEntity {
    private int lives = 3;
    private int score = 0;
    private final GameWindow gameWindow;
    private final InputHandler inputHandler;
    public CollisionChecker cChecker = new CollisionChecker();

    private static PacMan instancePacMan;

    private PacMan(GameWindow gw, InputHandler keyHandler) {
        this.gameWindow = gw;
        this.inputHandler = keyHandler;
        solidArea = new Rectangle(4, 4, 10, 10);
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
        setPositionX(gameWindow.getTileSize() * 12);
        setPositionY(gameWindow.getTileSize() * 20);
        speed = 2;
        direction = "down";
    }

    public void move() {
        if (inputHandler.upPressed || inputHandler.downPressed || inputHandler.leftPressed || inputHandler.rightPressed) {

            if (inputHandler.upPressed) {
                direction = "up";

            } else if (inputHandler.downPressed) {
                direction = "down";

            } else if (inputHandler.leftPressed) {
                direction = "left";

            } else if (inputHandler.rightPressed) {
                direction = "right";

            }
            collisionOn = false;
            cChecker.checkTile(this);
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        setPositionY(getPositionY() - speed);
                        break;
                    case "down":
                        setPositionY(getPositionY() + speed);
                        break;
                    case "left":
                        if (getPositionX() == 8) {
                            setPositionX(598);
                        } else {
                            setPositionX(getPositionX() - speed);
                        }
                        break;
                    case "right":
                        if (getPositionX() ==582) {
                            setPositionX(0);
                        } else {
                            setPositionX(getPositionX() + speed);
                            break;
                        }
                }
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
        }
    }

    private void loadPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_up_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_right_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_up_2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_down_2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_left_2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_right_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_up_3.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_down_3.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_left_3.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/pacmans/pac_man_right_3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int eaten() {
        lives--;
        return lives;
    }

    public int getLives() {
        return lives;
    }
}
