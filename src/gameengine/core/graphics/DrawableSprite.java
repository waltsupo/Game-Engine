package gameengine.core.graphics;

import gameengine.core.GameObject;
import gameengine.core.components.SpriteRenderer;
import gameengine.core.components.Transform;
import java.awt.Graphics2D;

/**
 * Object to draw image.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
class DrawableSprite extends DrawableObject {

    /**
     * What to draw.
     */
    private SpriteRenderer spriteRenderer;

    /**
     * Constructor.
     *
     * @param gameObject Related GameObject
     */
    DrawableSprite(GameObject gameObject) {

        this.gameObject = gameObject;
        spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        z = spriteRenderer.z;
    }

    /**
     * Draws image.
     *
     * @param g Graphics-object for drawing
     * @param delta Elapsed time since last update
     * @param camera Camera for checks
     */
    @Override
    void draw(Graphics2D g, float delta, Camera camera) {

        g.drawImage(spriteRenderer.image, (int) gameObject.transform.x,
                (int) (gameObject.transform.y + gameObject.transform.height),
                (int) gameObject.transform.width,
                (int) -gameObject.transform.height, null);
    }
}
