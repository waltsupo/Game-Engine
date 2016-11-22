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

            if (!animatedSprite.animation.isFinished) {
                if (animatedSprite.animation.loop) {
                    if (animatedSprite.animation.index >= animatedSprite.animation.images.length - 1) {
                        animatedSprite.animation.index = 0;
                    } else {
                        animatedSprite.animation.index++;
                    }
                } else {
                    if (animatedSprite.animation.index < animatedSprite.animation.images.length - 1) {
                        animatedSprite.animation.index++;
                    } else {
                        animatedSprite.animation.isFinished = true;
                    }
                }
            }
        }

        g.drawImage(animatedSprite.animation.source,
                (int) gameObject.transform.x,
                (int) (gameObject.transform.y + animatedSprite.animation.height),
                (int) (gameObject.transform.x + animatedSprite.animation.width),
                (int) gameObject.transform.y,
                animatedSprite.animation.images
                        [animatedSprite.animation.index][0],
                animatedSprite.animation.images
                        [animatedSprite.animation.index][1],
                animatedSprite.animation.images
                        [animatedSprite.animation.index][0]
                        + animatedSprite.animation.imageWidth,
                animatedSprite.animation.images
                        [animatedSprite.animation.index][1]
                        + animatedSprite.animation.imageHeight,
                null);
    }
}
