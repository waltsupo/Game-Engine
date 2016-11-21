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

    public Animation animation;

    /**
     * Constructor for component
     *
     * @param animation Animation to play
     * @param z Layer
     */
    public AnimatedSprite(Animation animation, int z) {

        this.animation = animation;
        this.z = z;
    }
}
