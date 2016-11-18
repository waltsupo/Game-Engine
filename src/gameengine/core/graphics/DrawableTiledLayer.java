package gameengine.core.graphics;

import gameengine.core.GameObject;
import gameengine.core.components.TiledMapRenderer;
import gameengine.utils.tiled.TiledMap;
import gameengine.utils.tiled.TiledMapLayer;

import java.awt.*;

/**
 * Object to draw TiledMapLayer.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
class DrawableTiledLayer extends DrawableObject {

    /**
     * Parent TiledMap.
     */
    private TiledMap map;

    /**
     * TiledMapLayer to draw.
     */
    private TiledMapLayer tiledLayer;

    /**
     * Constructor.
     *
     * @param gameObject Parent GameObject
     * @param tiledMapRenderer TiledMapRenderer to get necessary information
     * @param layer To what layer TiledMapLayer will be drawn
     */
    DrawableTiledLayer(GameObject gameObject,
                              TiledMapRenderer tiledMapRenderer,
                              TiledMapLayer layer) {

        this.gameObject = gameObject;
        map = tiledMapRenderer.tilemap;
        tiledLayer = layer;
        this.z = layer.z;
    }

    /**
     * Draws TiledMapLayer.
     *
     * @param g Graphics-object for drawing
     * @param camera Camera for checks
     */
    @Override
    void draw(Graphics2D g, Camera camera) {

        g.drawImage(tiledLayer.image, (int) map.x,
                (int) map.y,
                (int) map.width,
                (int) map.height, null);
    }
}
