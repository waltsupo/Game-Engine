package gameengine.mathlib;

import java.awt.geom.Rectangle2D;

/**
 * Rectangle object for the game.
 *
 * Used for example in collision detection.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1124
 * @since 1.7
 */
public class rect extends Rectangle2D {

    /**
     * X-coordinate of bottom-left corner.
     */
    public double x;

    /**
     * Y-coordinate of bottom-left corner.
     */
    public double y;

    /**
     * Width of the rectangle.
     */
    public double width;

    /**
     * Height of the rectangle.
     */
    public double height;

    /**
     * Defines new values for the rectangle.
     *
     * @param x New X-coordinate
     * @param y New Y-coordinate
     * @param w New width
     * @param h New height
     */
    @Override
    public void setRect(double x, double y, double w, double h) {

        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    /**
     * Tells where given point is located related to rectangle.
     *
     * Returns sum of directions, 0 if inside.
     *  - Left: 1
     *  - Right: 2
     *  - Bottom: 3
     *  - Top: 4
     *
     * For example, if point is bottom-left from rectangle, returns 4.
     *
     * @param x Point's X-coordinate
     * @param y Point's Y-coordinate
     * @return Sum of directions
     */
    @Override
    public int outcode(double x, double y) {

        int out = 0;

        // Left or right
        if (x < this.x) {
            out += 1;
        } else if (x > this.x + this.width) {
            out += 2;
        }

        // Top or bottom
        if (y < this.y) {
            out += 3;
        } else if (y > this.y + this.height) {
            out += 4;
        }
        return out;
    }

    @Override
    public Rectangle2D createIntersection(Rectangle2D r) {
        return null;
    }

    @Override
    public Rectangle2D createUnion(Rectangle2D r) {
        return null;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {

        return width;
    }

    @Override
    public double getHeight() {

        return height;
    }

    @Override
    public boolean isEmpty() {

        return false;
    }
}
