package gameengine.core.physics;

import gameengine.core.SubSystem;
import gameengine.core.components.Collider;
import gameengine.core.components.ComponentListener;
import gameengine.core.components.ComponentManager;
import gameengine.core.components.Transform;
import gameengine.core.GameObject;
import gameengine.mathlib.Rectangle;
import gameengine.core.components.Velocity;
import java.util.ArrayList;

/**
 * SubSystem to handle gravity for GameObjects.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1219
 * @since 1.7
 */
public class Gravity implements ComponentListener, SubSystem {

    /**
     * GameObjects that have Velocity-component.
     */
    private ArrayList<GameObject> objects;

    /**
     * Constructor for Gravity-SubSystem.
     */
    public Gravity() {

        objects = new ArrayList<>();
        ComponentManager.addCombination(this, Velocity.class, Collider.class,
                Transform.class);
    }

    /**
     * Updates Velocity-components.
     *
     * @param delta Elapsed time since last update
     */
    public void update(float delta) {

        for (GameObject gameObject : objects) {

            Velocity velocity = gameObject.getComponent(Velocity.class);

            if (velocity.onAir) {
                velocity.velocity.y = velocity.velocity.y
                        + (velocity.resistance.y * delta);
            } else {
                if (velocity.velocity.y != 0) {
                    velocity.onAir = true;
                } else {

                    Collider collider = gameObject.getComponent(Collider.class);
                    int layer = collider.layer;
                    Rectangle colRectangle = (Rectangle) collider.shape;
                    Rectangle feet = new Rectangle(colRectangle.x,
                            colRectangle.y - 5, colRectangle.width, 5);

                    if (!Physics.collides(feet, layer)) {
                        velocity.onAir = true;
                    }
                }
            }

            gameObject.translate(velocity.velocity.x * delta,
                    velocity.velocity.y * delta);
        }
    }

    /**
     * Adds new Velocity-component to be updated.
     *
     * @param gameObject GameObject that has required components
     * @param comClass Component class first on the combination list
     */
    public void newGameObject(GameObject gameObject, Class comClass) {

        objects.add(gameObject);
    }

    /**
     * Removes GameObject's Velocity from being updated.
     *
     * @param gameObject GameObject to remove
     */
    public void removeGameObject(GameObject gameObject) {

        objects.remove(gameObject);
    }
}
