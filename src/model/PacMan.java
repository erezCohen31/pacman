package model;

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

    private static PacMan instancePacMan;

    private PacMan(GameWindow gw, InputHandler keyHandler) {
        this.gameWindow = gw;
        this.inputHandler = keyHandler;
        solidArea = new Rectangle(10, 14, 30, 30);
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
        setPositionY(gameWindow.getTileSize() * 15);
        speed = 4;
        direction = "down";
    }

    public void move() {
        if (inputHandler.upPressed) {
            direction = "up";
            setPositionY(getPositionY() - speed);
        } else if (inputHandler.downPressed) {
            direction = "down";
            setPositionY(getPositionY() + speed);
        } else if (inputHandler.leftPressed) {
            direction = "left";
            setPositionX(getPositionX() - speed);
        } else if (inputHandler.rightPressed) {
            direction = "right";
            setPositionX(getPositionX() + speed);
        }
    }

    private void loadPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_up_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_right_1.png"));
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
