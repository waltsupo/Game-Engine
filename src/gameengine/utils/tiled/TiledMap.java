package gameengine.utils.tiled;

import java.util.LinkedList;
import java.util.List;

/**
 * Tiled map-object, based on TMX map format.
 *
 * Supports only orthogonal maps with right-down render order. Background
 * color is also unsupported.
 * CREATE NEW TILEDMAP USING MAPS.LOADTILEDMAP.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016-1117
 * @since 1.7
 */
public class TiledMap {

    /**
     * Map's left corner X-coordinate.
     */
    public float x;

    /**
     * Map's left corner Y-coordinate.
     */
    public float y;

    /**
     * Width of the map.
     */
    public float width;

    /**
     * Height of the map.
     */
    public float height;

    /**
     * General tile width.
     */
    public int tileWidth;

    /**
     * General tile height.
     */
    public int tileHeight;

    /**
     * How many columns does map have.
     */
    public int columns;

    /**
     * How many rows does map have.
     */
    public int rows;

    /**
     * List containing all TiledObjectGroups.
     */
    public List<TiledObjectGroup> objectLayers;

    /**
     * List containing all TileSets that are used.
     */
    public List<TileSet> tileSets;

    /**
     * List containing all tile layers.
     */
    public List<TiledMapLayer> layers;

    /**
     * Default constructor.
     */
    public TiledMap() {

        tileSets = new LinkedList<>();
        layers = new LinkedList<>();
        objectLayers = new LinkedList<>();

        x = 0;
        y = 0;
        width = 0;
        height = 0;
        tileWidth = 0;
        tileHeight = 0;
        rows = 0;
        columns = 0;
    }

    /**
     * Returns tileset containing given index.
     *
     * @param index Index to find
     * @return TileSet with index
     */
    public TileSet getTilesetWithTileIndex(int index) {

        TileSet toReturn = null;

        for (TileSet tileSet : tileSets) {

            if (tileSet.startIndex <= index
                    && index < tileSet.startIndex + tileSet.tileCount) {

                toReturn = tileSet;
                break;
            }
        }

        if (toReturn == null) {
            toReturn = tileSets.get(0);
        }

        return toReturn;
    }
}
