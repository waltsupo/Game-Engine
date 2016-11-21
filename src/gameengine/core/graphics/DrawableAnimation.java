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
class DrawableAnimation extends DrawableObject {

    /**
     * Animation component
     */
    private AnimatedSprite animatedSprite;

    private int index;

    private float time;

    /**
     * Defines values
     *
     * @param gameObject Parent GameObject
     */
    DrawableAnimation(GameObject gameObject) {

        this.gameObject = gameObject;
        animatedSprite = gameObject.getComponent(AnimatedSprite.class);
        z = animatedSprite.z;
        index = 0;
    }

    /**
     * Draws animation
     *
     * @param g Graphics-object for drawing
     * @param delta Elapsed time since last update
     * @param camera Camera for checks
     */
    void draw(Graphics2D g, float delta, Camera camera) {

        time += delta;
        while (time >= animatedSprite.animation.time) {
            time -= animatedSprite.animation.time;

            if (animatedSprite.animation.loop) {
                if (index >= animatedSprite.animation.images.length - 1) {
                    index = 0;
                } else {
                    index++;
                }
            } else {
                if (index < animatedSprite.animation.images.length - 1) {
                    index++;
                }
            }
        }

        g.drawImage(animatedSprite.animation.source,
                (int) gameObject.transform.x,
                (int) (gameObject.transform.y + gameObject.transform.height),
                (int) (gameObject.transform.x + gameObject.transform.width),
                (int) gameObject.transform.y,
                animatedSprite.animation.images[index][0],
                animatedSprite.animation.images[index][1],
                animatedSprite.animation.images[index][0]
                        + animatedSprite.animation.width,
                animatedSprite.animation.images[index][1]
                        + animatedSprite.animation.height,
                null);
    }
}
