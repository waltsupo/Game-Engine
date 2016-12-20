package example.scenes;

import example.systems.HealthSystem;
import gameengine.core.GameObject;
import gameengine.core.Scene;
import gameengine.core.graphics.Camera;
import gameengine.core.physics.Collisions;
import gameengine.utils.Job;
import gameengine.utils.Timer;
import gameengine.utils.tiled.TiledObject;
import gameengine.utils.tiled.TiledObjectGroup;
import example.gameobjects.*;
import gameengine.core.physics.Gravity;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Main Scene in game.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1219
 * @since 1.7
 */
public class GameScene extends Scene {

    /**
     * Player.
     */
    private Hero hero;

    /**
     * Walls.
     */
    private ArrayList<Platform> platforms;

    /**
     * Gravity-subsystem for possible later modification.
     */
    private Gravity gravity;

    /**
     * TiledMap as background.
     */
    private Map map;

    /**
     * Main camera to move.
     */
    private Camera camera;

    /**
     * Enemies in Scene.
     */
    public LinkedList<Enemy> enemies;

    /**
     * Spawner that spawns enemies.
     */
    private Spawner spawner;

    /**
     * Constructor for GameScene.
     */
    public GameScene() {

        gravity = new Gravity();
        addSubSystem(gravity);

        addSubSystem(new HealthSystem());

        platforms = new ArrayList<>();
        camera = getCamera();

        getSubSystem(Collisions.class).ignoreLayerCollision(4, 1);
        getSubSystem(Collisions.class).ignoreLayerCollision(4, 3);

        hero = new Hero(100, 100, 50, 80);
        enemies = new LinkedList<>();
        spawner = new Spawner(this);
        new Timer(2, true, new Job() {

            /**
             * Spawns new enemy.
             */
            public void job() {

                spawner.spawn();
            }
        });

        map = new Map();

        for (TiledObjectGroup layer : map.map.objectLayers) {

            if (layer.name.equals("Colliders")) {

                for (TiledObject object : layer.objects) {

                    platforms.add(new Platform(object.x, object.y, object.width,
                            object.height));
                }
            }
        }
    }

    /**
     * Updates scene.
     *
     * @param delta Elapsed time since last update
     */
    public void update(float delta) {

        hero.update(delta);

        for (Enemy enemy : enemies)
            enemy.update(delta, hero.transform);

        // Update camera
        float diffX = hero.transform.x + (hero.transform.width / 2)
                - camera.transform.x - (camera.transform.width / 2);
        float travelX = diffX * delta * 2;

        float diffY = hero.transform.y + (hero.transform.height / 2)
                - camera.transform.y - (camera.transform.height / 2);
        float travelY = diffY * delta * 2;

        camera.translate(travelX, travelY);

        if (camera.transform.x < 0) {
            camera.transform.x = 0;
        } if (camera.transform.y < 0) {
            camera.transform.y = 0;
        }
    }

    /**
     * Removes GameObject from the game.
     *
     * @param gameObject GameObject to remove
     */
    @Override
    public void removeGameObject(GameObject gameObject) {

        super.removeGameObject(gameObject);
        enemies.remove(gameObject);
    }
}
