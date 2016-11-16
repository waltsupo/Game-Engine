package gameengine.utils.tiled;

/**
 * Tiled map object
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1114
 * @since 1.7
 **/
public class TiledObject {

    /**
     * Name of the object
     */
    public String name;

    /**
     * Type of the object
     */
    public String type;

    /**
     * X-coordinate
     */
    public float x;

    /**
     * Y-coordinate
     */
    public float y;

    /**
     * Width of the object
     */
    public float width;

    /**
     * Height of the object
     */
    public float height;

    /**
     * Default constructor
     */
    public TiledObject() {

        name = "";
        type = "";
        x = 0;
        y = 0;
        width = 0;
        height = 0;
    }
}
