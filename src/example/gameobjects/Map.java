package example.gameobjects;

import gameengine.core.GameObject;
import gameengine.core.components.TiledMapRenderer;
import gameengine.utils.tiled.*;

/**
 * Background for the game.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1219
 * @since 1.7
 */
public class Map extends GameObject {

    /**
     * TiledMap object to draw and to get walls.
     */
    public TiledMap map;

    /**
     * Component for drawing.
     */
    private TiledMapRenderer renderer;

    /**
     * Defines values.
     */
    public Map() {

        transform.x = 0;
        transform.y = 0;
        transform.width = 200;
        transform.height = 200;

        try {
            map = Maps.loadTiledMap("tiledmap.tmx");
        } catch (Exception e) {
            e.printStackTrace();
        }

        renderer = new TiledMapRenderer(map);
        addComponent(renderer);
    }
}
