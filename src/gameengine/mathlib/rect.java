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


    /**
     * Creates rectangle from intersection of 2 rectangles.
     *
     * @param r Other rectangle
     * @return Intersection rectangle, null if no intersection
     */
    @Override
    public Rectangle2D createIntersection(Rectangle2D r) {

        rect rect = new rect();
        
        if (r.getX() <= x && r.getX() + r.getWidth() > x) {
            
            rect.x = x;
            
            if (r.getX() + r.getWidth() - x < width) {
                rect.width = r.getX() + r.getWidth() - x;
            } else {
                rect.width = width;
            }
        } else if (r.getX() > x && x + width < r.getX()) {
            
            rect.x = r.getX();

            if (x + width - r.getX() < r.getWidth()) {
                rect.width = x + width - r.getX();
            } else {
                rect.width = r.getWidth();
            }
        } else {
            
            // No intersection
            return null;
        }

        if (r.getY() <= y && r.getY() + r.getHeight() > y) {

            rect.y = y;

            if (r.getY() + r.getHeight() - y < height) {
                rect.height = r.getY() + r.getHeight() - y;
            } else {
                rect.height = height;
            }
        } else if (r.getY() > y && y + height < r.getY()) {

            rect.y = r.getY();

            if (y + height - r.getY() < r.getHeight()) {
                rect.height = y + height - r.getY();
            } else {
                rect.height = r.getHeight();
            }
        } else {

            // No intersection
            return null;
        }

        // If both x and y values defined for rectangle
        return rect;
    }

    @Override
    public Rectangle2D createUnion(Rectangle2D r) {
        return null;
    }

    /**
     * Gets X-coordinate
     *
     * @return Bottom-left corner X-coordinate
     */
    @Override
    public double getX() {

        return x;
    }

    /**
     * Gets Y-coordinate
     *
     * @return Bottom-left corner Y-coordinate
     */
    @Override
    public double getY() {

        return y;
    }

    /**
     * Gets width of the rectangle.
     *
     * @return width
     */
    @Override
    public double getWidth() {

        return width;
    }

    /**
     * Gets height of the rectangle.
     *
     * @return height
     */
    @Override
    public double getHeight() {

        return height;
    }

    @Override
    public boolean isEmpty() {

        return false;
    }
}
