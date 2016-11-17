package gameengine.mathlib;

/**
 * Abstract class for every shape.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public abstract class Shape {

    /**
     * X-coordinate.
     */
    public float x;

    /**
     * Y-coordinate.
     */
    public float y;

    /**
     * Looks if shape collides with other shape.
     *
     * @param shape Other shape
     * @return true if collides
     */
    abstract public boolean collides(Shape shape);
}
