package example.gameobjects;

import gameengine.core.components.Collider;
import gameengine.core.GameObject;
import gameengine.mathlib.Rectangle;

/**
 * Acts as a wall in game.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1219
 * @since 1.7
 */
public class Platform extends GameObject{

    /**
     * Collider of the wall.
     */
    public Collider collider;

    /**
     * Defines values.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param width Width of the object
     * @param height Height of the object
     */
    public Platform(float x, float y, float width, float height) {

        transform.x = x;
        transform.y = y;
        transform.width = width;
        transform.height = height;

        collider = new Collider(new Rectangle(x, y, width, height), 3);
        addComponent(collider);
    }
}
