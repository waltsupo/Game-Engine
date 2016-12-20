package gameengine.core.components;

import gameengine.mathlib.Vector;

/**
 * Contains information about object's velocity.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1219
 * @since 1.7
 */
public class Velocity extends Component {

    /**
     * Vector containing velocity on x and y -axis.
     */
    public Vector velocity;

    /**
     * Vector containing resistance values.
     *
     * Resistances tell how much should velocity drop in second.
     */
    public Vector resistance;

    /**
     * Tells if GameObject is on air currently, having no other Colliders below.
     */
    public boolean onAir = false;

    /**
     * Defines values for component.
     *
     * @param x Velocity on X-axis
     * @param y Velocity on Y-axis
     * @param resX Resistance on X-axis
     * @param resY Resistance on Y-axis
     */
    public Velocity(float x, float y, float resX, float resY) {

        active = true;
        velocity = new Vector(x, y);
        resistance = new Vector(resX, resY);
    }
}
