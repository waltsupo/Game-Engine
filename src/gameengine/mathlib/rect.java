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

    @Override
    public void setRect(double x, double y, double w, double h) {

        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

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
