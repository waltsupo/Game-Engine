package gameengine.core.graphics;

import gameengine.core.components.SpriteRenderer;
import gameengine.core.components.Transform;
import java.awt.Graphics2D;

/**
 * Object to draw image
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1111
 * @since 1.7
 */
public class DrawableSprite extends DrawableObject {

    /**
     * Location information
     */
    private Transform transform;

    /**
     * What to draw
     */
    private SpriteRenderer spriteRenderer;

    /**
     * Constructor
     *
     * @param transform Location
     * @param spriteRenderer What to draw
     */
    public DrawableSprite(Transform transform, SpriteRenderer spriteRenderer) {

        this.transform = transform;
        this.spriteRenderer = spriteRenderer;
        layer = spriteRenderer.layer;
    }

    /**
     * Draws image
     *
     * @param g Graphics-object for drawing
     * @param camera Camera for checks
     */
    @Override
    public void draw(Graphics2D g, Camera camera) {

        g.drawImage(spriteRenderer.image, (int) transform.x,
                (int) (transform.y + transform.height),
                (int) transform.width,
                (int) -transform.height, null);
    }
}
