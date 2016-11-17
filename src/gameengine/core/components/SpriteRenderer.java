package gameengine.core.components;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Component that draws image
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1002
 * @since 1.7
 */
public class SpriteRenderer extends DrawComponent {

    /**
     * Image to draw.
     */
    public Image image;

    /**
     * Defines attributes.
     *
     * @param image Image to draw
     * @param layer Layer where image should be drawn
     */
    public SpriteRenderer(Image image, int layer) {

        this.layer = (byte) layer;
        this.image = image;
    }
}
