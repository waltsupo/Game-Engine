package gameengine.mathlib;

/**
 * Abstract class for every shape.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public interface Shape {

    /**
     * Looks if shape collides with other shape.
     *
     * @param shape Other shape
     * @return True if collides
     */
    boolean collides(Shape shape);

    /**
     * Move shape to given location.
     *
     * @param x New X-coordinate
     * @param y New Y-coordinate
     */
    void moveTo(float x, float y);

    /**
     * Move shape by given amount.
     *
     * @param x Change on X-axis
     * @param y Change on Y-axis
     */
    void translate(float x, float y);
}
