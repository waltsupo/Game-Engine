package gameengine.mathlib;

/**
 * Point class
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Vector {

    /**
     * Point's x-coordinate
     */
    public float x;

    /**
     * Point's y-coordinate
     */
    public float y;

    /**
     * Defines values
     */
    public Vector() {

        x = 0;
        y = 0;
    }

    /**
     * Defines values
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     */
    public Vector(float x, float y) {

        this.x = x;
        this.y = y;
    }

    /**
     * Gets unit vector
     *
     * @return Unit vector
     */
    public Vector getUnit() {

        float length = length();

        return new Vector(x / length, y / length);
    }

    /**
     * Returns length of the vector
     *
     * @return Distance from (0,0)
     */
    public float length() {

        return (float) Math.sqrt(x*x + y*y);
    }

    /**
     * Returns string containing position information
     *
     * @return position
     */
    @Override
    public String toString() {

        return ("(" + x + ", " + y + ")");
    }
}
