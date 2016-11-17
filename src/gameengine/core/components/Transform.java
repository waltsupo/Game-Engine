package gameengine.core.components;

/**
 * Position component
 *
 * Contains information about position and dimensions
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Transform extends Component{

    /**
     * X-coordinate.
     */
    public float x;

    /**
     * Y-coordinate.
     */
    public float y;

    /**
     * Width of the GameObject.
     */
    public float width;

    /**
     * Height of the GameObject.
     */
    public float height;

    /**
     * Defines values for attributes.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param width Width
     * @param height Height
     */
    public Transform(float x, float y, float width, float height) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
    }
}
