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
        if (code == KeyEvent.VK_Z ) {
            upPressed = true;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            leftPressed = false;
            rightPressed = false;
            upPressed = false;
        }


        if (code == KeyEvent.VK_Q) {
            leftPressed = true;
            upPressed = false;
            downPressed = false;
            rightPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            leftPressed = false;
            upPressed = false;
            downPressed = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
