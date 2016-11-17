package gameengine.utils.tiled;

import gameengine.mathlib.Vector;
import gameengine.utils.Files;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * TileSet for TiledMap containing textures for tiles
 *
 * Doesn't support margins and spacings currently
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1114
 * @since 1.7
 */
public class TileSet {

    /**
     * Image with textures
     */
    public Image image;

    /**
     * TileSet's name
     */
    public String name;

    /**
     * Columns in TileSet
     */
    public int columns;

    /**
     * Tile width
     */
    public int tileWidth;

    /**
     * Tile height
     */
    public int tileHeight;

    /**
     * Total number of tile textures
     */
    public int tileCount;

    /**
     * Starting index, used to find right texture from many TileSets
     */
    public int startIndex;

    /**
     * Default constructor
     */
    public TileSet() {

        image = null;
        columns = 0;
        tileWidth = 0;
        tileHeight = 0;
        tileCount = 0;
        startIndex = 0;
        name = "";
    }

    /**
     * Loads image to be used on TileSet
     *
     * @param url path to image
     */
    public void setImage(String url) {

        // TODO Exception
        try {
            image = Files.loadImage(url);
        } catch (IOException e) {
            e.printStackTrace();  //----------------------------- FIX
        }
    }

    /**
     * Returns coordinates for given tile index to be used in drawing
     *
     * @param index Tile index
     * @return Coordinates to texture
     */
    public Vector getTileCoords(int index) {

        Vector coords = new Vector(0, 0);

        coords.y = (int) Math.floor(index / columns) * tileHeight;
        coords.x = (index % columns) * tileWidth;

        return coords;
        /*if (index >= startIndex && index < startIndex + tileCount) {
            Vector coords = new Vector(0, 0);

            coords.y = (int) Math.floor(index / columns) * tileHeight;
            coords.x = (index % columns) * tileWidth;

            return coords;
        } else {
            return null;
        }*/
    }
}
