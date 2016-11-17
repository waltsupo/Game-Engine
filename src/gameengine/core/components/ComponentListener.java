package gameengine.core.components;

import gameengine.core.GameObject;

/**
 * Interface for classes that need information about new components.
 *
 * These methods are called when new component is created or deleted.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1002
 * @since 1.7
 */
public interface ComponentListener {

    /**
     * Handles new GameObject that has required components.
     *
     * @param gameObject GameObject that has required components
     * @param comClass Component class first on the combination list
     */
    void newGameObject(GameObject gameObject, Class comClass);

    /**
     * Handles removing GameObject that no longer has required components.
     *
     * @param gameObject GameObject that no longer has required components
     * @param first First class on combination list, used to separate different
     *              combinations on one listener
     */
    void removeComponent(GameObject gameObject, Class first);
}
