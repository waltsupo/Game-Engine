package gameengine.core;

import gameengine.core.physics.Collisions;
import gameengine.core.graphics.Renderer;

import java.util.ArrayList;

/**
 * Scene, user can create multiple scenes and switch between those.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1002
 * @since 1.7
 */
public abstract class Scene {

    /**
     * Contains all GameObjects in the Scene
     */
    private ArrayList<GameObject> gameObjects;

    /**
     * Renderer that draws everything.
     */
    private Renderer renderer;

    /**
     * Contains all sub-systems
     */
    private ArrayList<SubSystem> systems;

    /**
     * Defines required values
     */
    public Scene() {

        gameObjects = new ArrayList<>();
        systems = new ArrayList<>();

        renderer = new Renderer();
        addSubSystem(new Collisions());
    }

    /**
     * Updates scene, handles engine side of update
     */
    public void updateScene() {

        update();

        for (SubSystem system : systems) {
            system.update();
        }
    }

    /**
     * Renders scene
     */
    public void render() {

        renderer.render();
    }

    /**
     * Updates scene, called once every update.
     */
    abstract public void update();


    /**
     * Removes references to GameObject
     *
     * @param gameObject GameObject to remove
     */
    public void deleteGameObject(GameObject gameObject) {

        // TODO remove at the end of frame?
        gameObjects.remove(gameObject);

        for (SubSystem system : systems) {
            system.deleteGameObject(gameObject);
        }

        renderer.deleteGameObject(gameObject);
    }

    /**
     * Adds new SubSystem to be updated
     *
     * @param system SubSystem to add
     */
    public void addSubSystem(SubSystem system) {

        boolean systemExists = false;

        for (SubSystem subSystem : systems) {

            if (subSystem.getClass() == system.getClass()) {
                systemExists = true;
            }
        }

        if (!systemExists) {

            systems.add(system);
        }
    }

    /**
     * Removes sub system of given class from the game loop
     *
     * @param systemClass Class to remove
     */
    public <T extends SubSystem> void deleteSubSystem(Class<T> systemClass) {

        for (SubSystem system : systems) {

            if (system.getClass() == systemClass) {

                systems.remove(system);
            }
        }
    }

    /**
     * Get system of given class currently in use
     *
     * @return System of given class
     */
    public <T extends SubSystem> T getSubSystem(Class<T> systemClass) {

        for (SubSystem system : systems) {
            if (system.getClass() == systemClass) {
                System.out.println("found");
                return (T) system;
            }
        }
        return null;
    }
}
