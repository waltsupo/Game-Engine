package gameengine.core.components;

import java.awt.*;

/**
 * Component that draws image
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class SpriteRenderer extends Component {

    /**
     * Image to draw.
     */
    public Image image;

    /**
     * Sprite's z-index
     */
    public int z;

    /**
     * Defines attributes.
     *
     * @param image Image to draw
     * @param z Layer where image should be drawn
     */
    public SpriteRenderer(Image image, int z) {

        this.z = z;
        this.image = image;
    }
}
