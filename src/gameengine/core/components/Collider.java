package gameengine.core.components;

import gameengine.mathlib.Shape;

/**
 * Collider-component for handling collisions.
 *
 * Collider has shape and layer to handle collision. Layer collision can be
 * changed using Physics-class
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Collider extends Component{

    /**
     * Collider's layer, layers can be used to ignore collision between
     * objects.
     */
    public int layer;

    /**
     * Shape of the Collider.
     */
    public Shape shape;

    /**
     * Constructor for Collider.
     *
     * @param shape Collider's shape and position information
     * @param layer Collider's layer
     */
    public Collider(Shape shape, int layer) {

        this.layer = layer;
        this.shape = shape;
    }
}
