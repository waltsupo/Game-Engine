package gameengine.core.components;

import gameengine.mathlib.ShapeFill;

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
    public ShapeFill fillType;

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
     * @param fillType how should shape be drawn
     * @param z Shape's layer
     * @param color Shape's color
     */
    public ShapeRenderer(int z, Color color, ShapeFill fillType) {

        active = true;
        this.fillType = fillType;
        this.color = color;
        this.z = z;
    }
}
