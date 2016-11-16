package gameengine.core;

/**
 * Interface for sub systems
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1511
 * @since 1.7
 */
public interface SubSystem {

    void update();
    void deleteGameObject(GameObject gameObject);
}
