package gameengine.core.graphics;

import gameengine.core.GameObject;
import gameengine.core.components.AnimatedSprite;

import java.awt.*;

/**
 * Object to draw animation
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1120
 * @since 1.7
 **/
class Animation extends DrawableObject {

    /**
     * Animation component
     */
    private AnimatedSprite animatedSprite;

    /**
     * Defines values
     *
     * @param gameObject Parent GameObject
     */
    Animation(GameObject gameObject) {

        animatedSprite = gameObject.getComponent(AnimatedSprite.class);
        z = animatedSprite.z;
    }

    /**
     * Draws animation
     *
     * @param g Graphics-object for drawing
     * @param camera Camera for checks
     */
    void draw(Graphics2D g, Camera camera) {

    }
}
