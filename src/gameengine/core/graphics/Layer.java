package gameengine.core.graphics;

import java.util.ArrayList;

/**
 * Layer that contains objects to draw.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
class Layer {

    /**
     * Layer's Z-index.
     */
    int z;

    /**
     * DrawableObjects to draw.
     */
    ArrayList<DrawableObject> drawables;

    /**
     * Constructor.
     *
     * @param layer Layer's Z-index
     */
    Layer(int layer) {

        z = layer;
        drawables = new ArrayList<>();
    }
}
