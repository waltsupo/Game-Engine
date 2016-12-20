package example.gameobjects;

import gameengine.core.GameObject;
import example.scenes.GameScene;

/**
 * Spawns enemies.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1219
 * @since 1.7
 */
public class Spawner extends GameObject {

    /**
     * Scene to spawn enemies to.
     */
    private GameScene scene;

    /**
     * Defines values.
     *
     * @param scene Scene with enemies
     */
    public Spawner(GameScene scene) {

        transform.x = 200;
        transform.y = 200;
        this.scene = scene;
    }

    /**
     * Spawns new enemy.
     */
    public void spawn() {

        scene.enemies.add(new Enemy(200, 200));
    }
}
