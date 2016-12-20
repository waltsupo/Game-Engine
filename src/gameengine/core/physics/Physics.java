package gameengine.core.physics;

import gameengine.core.Scene;
import gameengine.mathlib.Rectangle;

/**
 * Allows physics related tasks to be completed from anywhere anytime.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Physics {

    /**
     * Scene currently in use.
     */
    private static Scene scene;

    /**
     * Sets current scene.
     *
     * @param newScene New scene
     */
    public static void setScene(Scene newScene) {

        scene = newScene;
    }

    /**
     * Looks if rectangle collides with anything.
     *
     * @param rect Rectangle to check
     * @param layer Rectangle's layer
     * @return If collides
     */
    public static boolean collides(Rectangle rect, int layer) {

         return scene.getSubSystem(Collisions.class)
                .collides(rect, layer);
    }

    /**
     * Ignores collision between 2 layers.
     *
     * @param layer1 Layer 1
     * @param layer2 Layer 2
     */
    public static void ignoreLayerCollision(int layer1, int layer2) {

        scene.getSubSystem(Collisions.class)
                .ignoreLayerCollision(layer1, layer2);
    }
}
