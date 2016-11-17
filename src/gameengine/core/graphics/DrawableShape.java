package gameengine.core.graphics;

import gameengine.core.GameObject;
import gameengine.core.components.ShapeRenderer;
import gameengine.core.components.enums.ShapeType;
import gameengine.core.components.enums.Shapes;
import gameengine.mathlib.Rectangle;
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
     * @param camera Camera for checks
     */
    @Override
    void draw(Graphics2D g, Camera camera) {

        if (shapeRenderer.shape == Shapes.RECTANGLE) {
            Rectangle rect = new Rectangle(gameObject.transform.x,
                    gameObject.transform.y,
                    gameObject.transform.width, gameObject.transform.height);
            drawRectangle(g, rect, shapeRenderer.color, shapeRenderer.type);
        }
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
                               Color color, ShapeType type) {

        g.setColor(color);

        if (type == ShapeType.FILL) {
            g.fillRect((int) rectangle.x, (int)rectangle.y,
                    (int)rectangle.width, (int)rectangle.height);
        } else if (type == ShapeType.BORDER) {
            g.drawRect((int)rectangle.x, (int)rectangle.y,
                    (int)rectangle.width, (int)rectangle.height);
        }
    }
}
