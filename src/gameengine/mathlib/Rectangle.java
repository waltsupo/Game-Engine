package gameengine.mathlib;

/**
 * Rectangle class for collisions and drawing.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1117
 * @since 1.7
 */
public class Rectangle extends Shape{

    /**
     * Width.
     */
    public float width;

    /**
     * Height.
     */
    public float height;

    /**
     * Defines values.
     */
    public Rectangle() {

        x = 0;
        y = 0;
        width = 0;
        height = 0;
    }

    /**
     * Defines values.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param width Width
     * @param height Height
     */
    public Rectangle(float x, float y, float width, float height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Tells if rectangle contains given point.
     *
     * @param x Point's x-coordinate
     * @param y Point's y-coordinate
     * @return true if contains point
     */
    public Boolean contains(float x, float y) {

        Boolean contains = false;

        if ((this.x <= x && this.x + width >= x)
                && this.y <= y && this.y + height >= y) {
            contains = true;
        }

        return contains;
    }

    /**
     * Looks if rectangle collides with given shape.
     *
     * @param shape Other shape
     * @return true if collides
     */
    public boolean collides(Shape shape) {

        boolean collides = false;

        if (shape.getClass() == Rectangle.class) {
            collides = collides((Rectangle) shape);
        }

        return collides;
    }

    /**
     * Looks if rectangle collides with other rectangle.
     *
     * @param rect Other rectangle
     * @return true if collides
     */
    public boolean collides(Rectangle rect) {

        Boolean collision = false;

        if (contains(rect.x, rect.y) || contains(rect.x + rect.width, rect.y)
                || contains(rect.x, rect.y + rect.height) || contains(rect.x
                + rect.width, rect.y + rect.height)) {
            collision = true;
        } else if (rect.contains(x, y) || rect.contains(x + width, y)
                || rect.contains(x, y + height) || rect.contains(x + width, y
                + height)) {
            collision = true;
        }

        return collision;
    }
}
