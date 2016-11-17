package gameengine.core;

/**
 * Interface for sub systems.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public interface SubSystem {

    /**
     * Updates sub-system, called once per Scene update.
     *
     * @param delta Elapsed time since last update
     */
    void update(float delta);

    /**
     * Removes GameObject from the system.
     *
     * @param gameObject GameObject to remove
     */
    void removeGameObject(GameObject gameObject);
}
