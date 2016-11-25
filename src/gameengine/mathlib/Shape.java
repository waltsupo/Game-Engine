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
     * Code that tells renderer to draw only borders.
     */
    public static byte BORDERS = 4;

    /**
     * COde that tells renderer to draw whole shape.
     */
    public static byte FILL = 8;

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
