package gameengine.core.input;

import gameengine.core.GameManager;
import gameengine.mathlib.Vector;

import java.awt.event.*;

/**
 * Handles registering input
 *
 * Contains methods to get states of keys
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class InputManager implements KeyListener, MouseListener,
        MouseMotionListener{

    /**
     * States of all keys, are they down
     */
    private static Boolean[] keys;

    /**
     * States of mouse buttons
     */
    private static Boolean[] mouse;

    /**
     * Current position of mouse, updated when pressed
     */
    private static Vector mousePos;

    /**
     * Defines values for attributes
     */
    public InputManager () {
        keys = new Boolean[256];

        for (int i = 0; i < 256; i++)
            keys[i] = false;

        mouse = new Boolean[1];

        for (int i = 0; i < 1; i++)
            mouse[i] = false;

        mousePos = new Vector(0,0);
    }

    /**
     * Handles KeyTyped event
     *
     * @param e KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Handles KeyPressed event
     *
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {

        keys[e.getKeyCode()] = true;
    }

    /**
     * Handles KeyReleased event
     *
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {

        keys[e.getKeyCode()] = false;
    }

    /**
     * Handles MouseClicked event
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        mousePos.x = e.getX();
        mousePos.y = GameManager.getHeight() - e.getY();
    }

    /**
     * Handles MousePressed event
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {

        mousePos.x = e.getX();
        mousePos.y = GameManager.getHeight() -e.getY();
        mouse[0] = true;
    }

    /**
     * Handles MouseReleased event
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        mousePos.x = e.getX();
        mousePos.y = GameManager.getHeight() - e.getY();
        mouse[0] = false;
    }

    /**
     * Handles MouseEntered event
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Handles MouseExited event
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Handles MouseDragged event
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        mousePos.x = e.getX();
        mousePos.y = GameManager.getHeight() -e.getY();
    }

    /**
     * Handles MouseMoved event
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Tells if given key is down
     *
     * @param keyCode Keycode of key
     * @return if key is down
     */
    public static boolean isKeyDown(int keyCode) {

        return keys[keyCode];
    }

    /**
     * Tells if mouse key is down
     *
     * @return if mouse button is down
     */
    public static boolean isMouseDown() {
        return mouse[0];
    }

    /**
     * Gives current position of cursor
     *
     * @return postion of cursor
     */
    public static Vector getMousePos() {

        return mousePos;
    }
}
