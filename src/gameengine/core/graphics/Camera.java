package gameengine.core.graphics;

import gameengine.core.components.Transform;
import gameengine.core.GameObject;

/**
 * Object to handle moving view.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1002
 * @since 1.7
 */
public class Camera {

    /**
     * Position information
     */
    public Transform transform;

    /**
     * GameObject to follow
     */
    public GameObject gameObject;

    /**
     * Constructor for camera
     */
    public Camera() {

        transform = new Transform(0, 0, 800, 600);
        gameObject = null;
    }

    /**
     * Starts following given GameObject
     *
     * @param gameObject GameObject to follow
     */
    public void followObject(GameObject gameObject) {

        this.gameObject = gameObject;
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

    /**
     * Updates camera position when following GameObject
     */
    public void update() {

        if (gameObject != null) {
            transform.x = gameObject.transform.x - transform.width / 2;
            transform.y = gameObject.transform.y - transform.height / 2;
        }
    }
}
