package model;

import control.InputHandler;
import view.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PacMan extends GameEntity {
    public int lives = 3;
    int score = 0;
    private final GameWindow gameWindow;
    private final InputHandler inputHandler;

    public PacMan(GameWindow gw, InputHandler keyH) {
        this.gameWindow = gw;

        this.inputHandler = keyH;


        solidArea = new Rectangle(10, 14, 30, 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        loadPlayerImage();
    }

    public void setDefaultValues() {
        setPositionX(gameWindow.getTileSize() * 12);
        setPositionY(gameWindow.getTileSize() * 15);
        speed = 4;
        direction = "down";
    }

    public void loadPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_up_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_down_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_left_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/model/pac_man_right_3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int eaten() {
        lives--;
        return lives;
    }
}

