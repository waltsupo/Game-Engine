package gameengine.core.graphics;

import gameengine.core.GameObject;

import java.awt.*;

/**
 * Parent class for Drawable-objects
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
abstract class DrawableObject {

    /**
     * Related GameObject
     */
    GameObject gameObject;

    /**
     * Object's layer
     */
    int z;

    /**
     * Draws object
     *
     * @param g Graphics-object for drawing
     * @param camera Camera for checks
     */
    abstract void draw(Graphics2D g, Camera camera);
}
