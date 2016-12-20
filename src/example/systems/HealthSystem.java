package example.systems;

import example.components.Health;
import gameengine.core.GameObject;
import gameengine.core.SubSystem;
import gameengine.core.components.ComponentListener;
import gameengine.core.components.ComponentManager;

import java.util.LinkedList;

/**
 * SubSystem that handles Health-components.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1220
 * @since 1.7
 **/
public class HealthSystem implements ComponentListener, SubSystem {

    /**
     * GameObjects to update.
     */
    private LinkedList<GameObject> gameObjects;

    /**
     * Defines values.
     */
    public HealthSystem() {

        gameObjects = new LinkedList<>();
        ComponentManager.addCombination(this, Health.class);
    }

    /**
     * Updates Health-components and removes GameObject if health below 0.
     *
     * @param delta Elapsed time since last update
     */
    @Override
    public void update(float delta) {

        LinkedList<GameObject> toRemove = new LinkedList<>();

        for (GameObject gameObject : gameObjects) {

            Health health = gameObject.getComponent(Health.class);

            if (health.amount <= 0) {
                toRemove.add(gameObject);
            }
        }

        for (GameObject go : toRemove) {

            gameObjects.remove(go);
            go.remove();
        }
    }

    /**
     * Adds new Health component to be updated.
     *
     * @param gameObject GameObject that has required components
     * @param comClass Component class first on the combination list
     */
    @Override
    public void newGameObject(GameObject gameObject, Class comClass) {

        gameObjects.add(gameObject);
    }

    /**
     * Removes health component from being updated.
     *
     * @param gameObject GameObject to remove
     */
    @Override
    public void removeGameObject(GameObject gameObject) {

        gameObjects.remove(gameObject);
    }
}
