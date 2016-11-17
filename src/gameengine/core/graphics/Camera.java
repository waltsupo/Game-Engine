package gameengine.core.graphics;

import gameengine.core.components.Transform;

/**
 * Object to handle moving view
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Camera {

    /**
     * Position information
     */
    public Transform transform;

    /**
     * Constructor for camera
     */
    public Camera() {

        transform = new Transform(0, 0, 800, 600);
    }

    /**
     * Moves camera position by given amount.
     *
     * @param x Change on x-axis
     * @param y Change on y-axis
     */
    public void translate(float x, float y) {

        transform.x += x;
        transform.y += y;
    }

    /**
     * Moves camera to given position
     *
     * @param x New x-coordinate
     * @param y New y-coordinate
     */
    public void moveTo(float x, float y) {

        transform.x = x;
        transform.y = y;
    }
}
