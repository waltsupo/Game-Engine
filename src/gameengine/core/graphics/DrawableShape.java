package gameengine.core.graphics;

import gameengine.core.components.ShapeRenderer;
import gameengine.core.components.Transform;
import gameengine.core.components.enums.ShapeType;
import gameengine.core.components.enums.Shapes;
import gameengine.mathlib.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Object to draw shape
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1111
 * @since 1.7
 */
public class DrawableShape extends DrawableObject {

    /**
     * Location information
     */
    private Transform transform;

    /**
     * What to draw
     */
    private ShapeRenderer shapeRenderer;

    /**
     * Constructor
     *
     * @param transform Location information
     * @param shapeRenderer What to draw
     */
    public DrawableShape(Transform transform, ShapeRenderer shapeRenderer) {

        this.transform = transform;
        this.shapeRenderer = shapeRenderer;
        layer = shapeRenderer.layer;
    }

    /**
     * Draws Shape
     *
     * @param g Graphics-object for drawing
     * @param camera Camera for checks
     */
    @Override
    public void draw(Graphics2D g, Camera camera) {

        if (shapeRenderer.shape == Shapes.RECTANGLE) {
            Rectangle rect = new Rectangle(transform.x,
                    transform.y,
                    transform.width, transform.height);
            drawRectangle(g, rect, shapeRenderer.color, shapeRenderer.type);
        }
    }

    /**
     * Draws rectangle
     *
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
