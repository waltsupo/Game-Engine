package gameengine.core.components;

import gameengine.core.graphics.Animation;

/**
 * Component for animated components.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1120
 * @since 1.7
 **/
public class AnimatedSprite extends Component {

    /**
     * Layer to draw.
     */
    public int z;

    /**
     * Animation to play.
     */
    public Animation animation;

    /**
     * Constructor for component.
     *
     * @param animation Animation to play
     * @param z Layer
     */
    public AnimatedSprite(Animation animation, int z) {

        active = true;
        this.animation = animation;
        this.z = z;
    }
}
