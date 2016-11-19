package gameengine.core;

import gameengine.core.graphics.Camera;
import gameengine.core.physics.Collisions;
import gameengine.core.graphics.Renderer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Scene, user can create multiple scenes and switch between those.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public abstract class Scene {

    /**
     * Contains all GameObjects in the Scene.
     */
    private ArrayList<GameObject> gameObjects;

    /**
     * Renderer that draws everything.
     */
    private Renderer renderer;

    /**
     * Contains all sub-systems, updated from newest to oldest.
     */
    final private LinkedList<SubSystem> systems;

    /**
     * Defines required values.
     */
    public Scene() {

        gameObjects = new ArrayList<>();
        systems = new LinkedList<>();

        renderer = new Renderer();
        addSubSystem(new Collisions());
    }

    /**
     * Updates scene, handles engine side of update.
     *
     * @param delta Elapsed time since last update
     */
    void updateScene(float delta) {

        update(delta);

        for (SubSystem system : systems) {
            system.update(delta);
        }
    }

    /**
     * Renders scene.
     */
    void render() {

        renderer.render();
    }

    /**
     * Updates scene, called once every update.
     *
     * @param delta Elapsed time since last update
     */
    abstract public void update(float delta);

    /**
     * Removes references to GameObject.
     *
     * @param gameObject GameObject to remove
     */
    public void removeGameObject(GameObject gameObject) {

        gameObjects.remove(gameObject);

        for (SubSystem system : systems) {
            system.removeGameObject(gameObject);
        }

        renderer.deleteGameObject(gameObject);
    }

    /**
     * Adds new SubSystem to be updated.
     *
     * @param system SubSystem to add
     */
    protected void addSubSystem(SubSystem system) {

        boolean systemExists = false;

        for (SubSystem subSystem : systems) {

            if (subSystem.getClass() == system.getClass()) {
                systemExists = true;
            }
        }

        if (!systemExists) {

            systems.addFirst(system);
        }
    }

    /**
     * Gets system of given class currently in use.
     *
     * @param systemClass Class of wanted system
     * @return System of given class
     */
    public <T extends SubSystem> T getSubSystem(Class<T> systemClass) {

        for (SubSystem system : systems) {
            if (system.getClass() == systemClass) {

                return systemClass.cast(system);
            }
        }

        return null;
    }

    /**
     * Gets camera from the renderer.
     *
     * @return Camera currently in use
     */
    public Camera getCamera() {

        return renderer.getCamera();
    }
}
