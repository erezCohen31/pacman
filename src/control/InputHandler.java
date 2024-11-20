package control;

import model.PacMan;
import view.GameWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;


    private InputHandler() {
    }

    static InputHandler instanceKeyH = new InputHandler();

    public static InputHandler getInstance() {
        return instanceKeyH;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_Z:
                upPressed = true;
                downPressed = false;
                rightPressed = false;
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                upPressed = false;
                rightPressed = false;
                leftPressed = false;
                break;
            case KeyEvent.VK_Q:
                leftPressed = true;
                upPressed = false;
                downPressed = false;
                rightPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                upPressed = false;
                downPressed = false;
                leftPressed = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {    }
}
