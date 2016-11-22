package gameengine.core.components;

import gameengine.core.graphics.Animation;

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
     * Layer to draw
     */
    public int z;
    public int width;
    public int height;

    public Animation animation;

    /**
     * Constructor for component
     *
     * @param animation Animation to play
     * @param width Width of the image
     * @param height Height of the image
     * @param offsetX Bottom-left corner distance from GameObject
     * @param offsetY Bottom-left corner distance from GameObject
     * @param z Layer
     */
    public AnimatedSprite(Animation animation, int width, int height, int offsetX, int offsetY, int z) {

        this.animation = animation;
        this.width = width;
        this.height = height;
        this.z = z;
    }
}
