package gameengine.core.components;

import gameengine.core.components.enums.ShapeType;
import gameengine.core.components.enums.Shapes;

import java.awt.*;

/**
 * Component that draws shape.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1002
 * @since 1.7
 */
public class ShapeRenderer extends DrawComponent {

    /**
     * Shape to draw
     */
    public Shapes shape;

    /**
     * Should shape be filled or just borders
     */
    public ShapeType type;

    /**
     * Shape's color.
     */
    public Color color;

    /**
     * Defines values for attributes.
     *
     * @param shape Shape to draw
     * @param type Fill type
     * @param layer Shape's layer
     * @param color Shape's color
     */
    public ShapeRenderer(Shapes shape, ShapeType type, int layer, Color color) {

        this.shape = shape;
        this.type = type;
        this.color = color;
        this.layer = layer;
    }
}
