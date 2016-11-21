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

    private int row;

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
        while (time >= animatedSprite.time) {
            time -= animatedSprite.time;
            index++;

            if (index >= animatedSprite.images.length) {
                index = 0;
            }
        }

        g.drawImage(animatedSprite.spritesheet,
                (int) gameObject.transform.x,
                (int) (gameObject.transform.y + gameObject.transform.height),
                (int) (gameObject.transform.x + gameObject.transform.width),
                (int) gameObject.transform.y,
                animatedSprite.images[index][0],
                animatedSprite.images[index][1],
                animatedSprite.images[index][0] + animatedSprite.width,
                animatedSprite.images[index][1] + animatedSprite.height,
                null);
    }
}
