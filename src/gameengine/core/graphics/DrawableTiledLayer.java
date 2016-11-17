package gameengine.core.graphics;

import gameengine.core.Game;
import gameengine.core.GameObject;
import gameengine.core.components.TiledMapRenderer;
import gameengine.mathlib.Vector;
import gameengine.utils.tiled.TileSet;
import gameengine.utils.tiled.TiledMap;
import gameengine.utils.tiled.TiledMapLayer;

import java.awt.*;

/**
 * Object to draw TiledMapLayer
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1111
 * @since 1.7
 */
public class DrawableTiledLayer extends DrawableObject {

    /**
     * Reference to original tiledMapRenderer
     */
    private TiledMapRenderer tiledMapRenderer;

    /**
     * Parent TiledMap
     */
    private TiledMap map;

    /**
     * TiledMapLayer to draw
     */
    private TiledMapLayer tiledLayer;

    /**
     * Constructor
     *
     * @param tiledMapRenderer TiledMapRenderer to get necessary information
     * @param layer To what layer TiledMapLayer will be drawn
     */
    public DrawableTiledLayer(GameObject gameObject,
                              TiledMapRenderer tiledMapRenderer,
                              TiledMapLayer layer) {

        this.gameObject = gameObject;
        this.tiledMapRenderer = tiledMapRenderer;
        map = tiledMapRenderer.tilemap;
        tiledLayer = layer;
        this.z = layer.z;
    }

    // TODO Draw only visible part
    /**
     * Draws TiledMapLayer
     *
     * @param g Graphics-object for drawing
     * @param camera Camera for checks
     */
    @Override
    public void draw(Graphics2D g, Camera camera) {

        g.drawImage(tiledLayer.image, (int) map.x,
                (int) map.y,
                (int) map.width,
                (int) map.height, null);
    }
}
