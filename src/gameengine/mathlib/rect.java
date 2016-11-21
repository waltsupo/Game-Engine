package gameengine.mathlib;

import java.awt.geom.Rectangle2D;

/**
 * TODO description
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version %I%, %G%
 */
public class rect extends Rectangle2D {

    public double x;
    public double y;
    public double width;
    public double height;

    /**
     * Defines new values
     *
     * @param x New X-coordinate
     * @param y
     * @param w
     * @param h
     */
    @Override
    public void setRect(double x, double y, double w, double h) {

        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    @Override
    public int outcode(double x, double y) {
        return 0;
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
