package gameengine.core.graphics;

import gameengine.core.GameObject;
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
     * What to draw
     */
    private SpriteRenderer spriteRenderer;

    /**
     * Constructor
     *
     * @param gameObject Related GameObject
     * @param spriteRenderer What to draw
     */
    public DrawableSprite(GameObject gameObject, SpriteRenderer spriteRenderer) {

        this.gameObject = gameObject;
        this.spriteRenderer = spriteRenderer;
        z = spriteRenderer.layer;
    }

    /**
     * Draws image
     *
     * @param g Graphics-object for drawing
     * @param camera Camera for checks
     */
    @Override
    public void draw(Graphics2D g, Camera camera) {

        g.drawImage(spriteRenderer.image, (int) gameObject.transform.x,
                (int) (gameObject.transform.y + gameObject.transform.height),
                (int) gameObject.transform.width,
                (int) -gameObject.transform.height, null);
    }
}
