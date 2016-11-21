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

    public int[][] images = {{0, 495}, {536, 495}, {536 * 2, 495}, {536 * 3, 495}, {536 * 4, 495}, {0, 0}, {536 * 1, 0}, {536 * 2, 0}, {536 * 3, 0}, {536 * 4, 0}};

    /**
     * Constructor for component
     *
     * @param spritesheet Image that contains all frames
     * @param width Width of one image
     * @param height Height of one image
     * @param time Time between images
     * @param z Layer
     */
    public AnimatedSprite
            (Image spritesheet, int width, int height, float time, int z) {

        this.spritesheet = spritesheet;
        this.width = width;
        this.height = height;
        this.time = time;
        this.z = z;
    }
}
