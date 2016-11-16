package gameengine.utils.tiled;

import gameengine.mathlib.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * TiledMapLayer for TileMap, contains tiles to draw
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1114
 * @since 1.7
 */
public class TiledMapLayer {

    /**
     * Array of tile indexes, order to render is right-down
     */
    public int[] tiles;

    /**
     * Tiled layer's name
     */
    public String name;

    /**
     * Layer's width
     */
    public int width;

    /**
     * Layer's height
     */
    public int height;

    /**
     * Layer's Z-index
     */
    public int z;

    /**
     * Image to make drawing faster, layer's tiles will be drawn to this when
     * drawLayerImage() is called
     */
    public Image image;

    /**
     * Creates new image containing all tiles
     *
     * @param map Parent TiledMap
     */
    public void drawLayerImage(TiledMap map) {

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        BufferedImage layerImage = config.createCompatibleImage(
                width * map.tileWidth,
                height * map.tileHeight,
                Transparency.TRANSLUCENT);


        Graphics g = layerImage.getGraphics();
        TileSet tileset = map.tileSets.get(0);
        Vector tileCoords;

        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            for (int colIndex = 0; colIndex < width; colIndex++) {

                if (tiles[rowIndex * width + colIndex] == -1) {
                    continue;
                }

                // Get tile's coordinates on tile set
                tileCoords = tileset.getTileCoords(
                        tiles[rowIndex * width + colIndex]);

                g.drawImage(tileset.image,
                        (int) map.x + colIndex * map.tileWidth,
                        (int) (map.y + map.height
                                - ((rowIndex + 1) * map.tileHeight)),
                        (int) map.x + (colIndex + 1) * map.tileWidth,
                        (int) (map.y + map.height
                                - (rowIndex * map.tileHeight)),
                        (int) tileCoords.x,
                        (int) tileCoords.y + tileset.tileHeight,
                        (int) tileCoords.x + tileset.tileWidth,
                        (int) tileCoords.y,
                        null);
            }
        }

        image = layerImage;
    }

    /**
     * Sets z-index of this layer
     *
     * @param z New z-index
     */
    public void setZ(int z) {

        if (z == this.z) {

            return;
        }

        this.z = z;
    }
}
