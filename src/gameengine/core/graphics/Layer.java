package gameengine.core.graphics;

import java.util.ArrayList;

/**
 * Layer that contains objects to draw
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1111
 * @since 1.7
 */
public class Layer {

    /**
     * Layer's Z-index
     */
    public int z;

    /**
     * DrawableObjects to draw
     */
    public ArrayList<DrawableObject> drawables;

    /**
     * Constructor
     *
     * @param layer Layer's Z-index
     */
    public Layer(int layer) {

        z = layer;
        drawables = new ArrayList<>();
    }
}
