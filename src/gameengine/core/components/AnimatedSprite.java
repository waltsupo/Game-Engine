package gameengine.core.components;

import java.awt.*;

/**
 * Component for animated components
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1120
 * @since 1.7
 **/
public class AnimatedSprite extends Component {

    /**
     * Spritesheet containing all frames
     */
    public Image spritesheet;

    /**
     * Width of one image
     */
    public int width;

    /**
     * Height of one image
     */
    public int height;

    /**
     * Time between images
     */
    public float time;

    /**
     * Layer to draw
     */
    public int z;

    /**
     * Constructor for component
     *
     * @param image Image that contains all frames
     * @param width Width of one image
     * @param height Height of one image
     * @param time Time between images
     * @param z Layer
     */
    public AnimatedSprite
            (Image image, int width, int height, float time, int z) {

        spritesheet = image;
        this.width = width;
        this.height = height;
        this.time = time;
        this.z = z;
    }
}
