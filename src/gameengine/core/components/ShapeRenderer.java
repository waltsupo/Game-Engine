package gameengine.core.components;

import gameengine.core.components.enums.ShapeType;

import java.awt.*;

/**
 * Component that draws shape.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class ShapeRenderer extends Component {

    /**
     * Should shape be filled or just borders.
     */
    public ShapeType type;

    /**
     * Shape's color.
     */
    public Color color;

    /**
     * Shape's z-index.
     */
    public int z;

    /**
     * Defines values for attributes.
     *
     * @param type Fill type
     * @param z Shape's layer
     * @param color Shape's color
     */
    public ShapeRenderer(ShapeType type, int z, Color color) {

        this.type = type;
        this.color = color;
        this.z = z;
    }
}
