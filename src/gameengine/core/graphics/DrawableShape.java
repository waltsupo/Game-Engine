package gameengine.core.graphics;

import gameengine.core.GameObject;
import gameengine.core.components.ShapeRenderer;
import gameengine.mathlib.Rectangle;
import gameengine.mathlib.Shape;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Object to draw shape.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
class DrawableShape extends DrawableObject {

    /**
     * What to draw.
     */
    private ShapeRenderer shapeRenderer;

    /**
     * Constructor.
     *
     * @param gameObject Related GameObject
     */
    DrawableShape(GameObject gameObject) {

        this.gameObject = gameObject;
        shapeRenderer = gameObject.getComponent(ShapeRenderer.class);
        z = shapeRenderer.z;
    }

    /**
     * Draws Shape.
     *
     * @param g Graphics-object for drawing
     * @param delta Elapsed time since last update
     * @param camera Camera for checks
     */
    @Override
    void draw(Graphics2D g, float delta, Camera camera) {

        Rectangle rect = new Rectangle(gameObject.transform.x,
                gameObject.transform.y,
                gameObject.transform.width, gameObject.transform.height);
        drawRectangle(g, rect, shapeRenderer.color, shapeRenderer.fillType);
    }

    /**
     * Draws rectangle.
     *
     * @param g Graphics-object for drawing
     * @param rectangle Rectangle to draw
     * @param color Color
     * @param type Fill type
     */
    private void drawRectangle(Graphics2D g, Rectangle rectangle,
                               Color color, int type) {

        g.setColor(color);

        if (type == Shape.FILL) {
            g.fillRect((int) rectangle.x, (int)rectangle.y,
                    (int)rectangle.width, (int)rectangle.height);
        } else if (type == Shape.BORDERS) {
            g.drawRect((int)rectangle.x, (int)rectangle.y,
                    (int)rectangle.width, (int)rectangle.height);
        }
    }
}
