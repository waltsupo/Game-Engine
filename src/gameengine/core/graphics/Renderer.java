package gameengine.core.graphics;

import gameengine.core.*;
import gameengine.core.components.*;
import gameengine.core.Window;
import gameengine.utils.tiled.TiledMapLayer;
import java.awt.*;
import java.util.ArrayList;

/**
 * Handles Drawing.
 *
 * Draws all objects that have required components to the screen.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Renderer implements ComponentListener {

    /**
     * Window for drawing.
     */
    private Window window;

    /**
     * graphics object for drawing.
     */
    private Graphics2D g;

    /**
     * Camera for view.
     */
    private static Camera camera;

    /**
     * Layers to draw.
     */
    private ArrayList<Layer> layers;

    /**
     * Background color, default black.
     */
    private Color background;

    /**
     * Defines values and starts listening for component information.
     */
    public Renderer() {

        window = GameManager.getGame().getWindow();
        layers = new ArrayList<>();
        camera = new Camera();
        window.setCamera(camera);
        background = Color.BLACK;

        // Listen component changes
        ComponentManager.addCombination(this, ShapeRenderer.class,
                Transform.class);
        ComponentManager.addCombination(this, SpriteRenderer.class,
                Transform.class);
        ComponentManager.addCombination(this, TiledMapRenderer.class);
        ComponentManager.addCombination(this, AnimatedSprite.class,
                Transform.class);
    }

    /**
     * Draws to screen.
     *
     * @param delta Elapsed time since last update
     */
    public void render(float delta) {

        // create and get graphics

        g = window.start();

        // Background color
        g.setColor(background);
        g.fillRect(0, 0, GameManager.getWidth(), GameManager.getHeight());

        g.translate(-camera.transform.x, -camera.transform.y);

        // Draw layers
        for (Layer layer : layers) {
            for (DrawableObject drawable : layer.drawables) {

                drawable.draw(g, delta, camera);
            }
        }

        g.scale(1, -1);

        // Update window
        window.stop();
    }

    /**
     * Returns current camera.
     *
     * @return Camera used by renderer
     */
    public Camera getCamera() {

        return camera;
    }

    /**
     * Adds DrawableObject to correct layer to be drawn.
     *
     * @param gameObject GameObject that has required components
     * @param comClass Component class first on the combination list
     */
    public void newGameObject(GameObject gameObject, Class comClass) {

        if (comClass == SpriteRenderer.class) {

            addDrawable(new DrawableSprite(gameObject));
        } else if (comClass == AnimatedSprite.class) {

            addDrawable(new DrawableAnimation(gameObject));
        } else if (comClass == ShapeRenderer.class) {

            addDrawable(new DrawableShape(gameObject));
        } else if (comClass == TiledMapRenderer.class) {

            TiledMapRenderer mapRenderer =
                    gameObject.getComponent(TiledMapRenderer.class);

            for (TiledMapLayer layer : mapRenderer.tilemap.layers) {
                addDrawable(new DrawableTiledLayer(
                        gameObject, mapRenderer, layer));
            }
        }
    }

    /**
     * Adds drawable to correct layer.
     *
     * @param drawable Drawable to add
     */
    private void addDrawable(DrawableObject drawable) {

        boolean found = false;

        // If has layers, else create new one
        if (layers.size() != 0) {

            for (Layer layer : layers) {

                // If layer with same Z-index is found
                if (layer.z == drawable.z) {

                    layer.drawables.add(drawable);
                    found = true;
                    break;
                }
            }

            // If no layer with same Z-index, create new layer
            if (!found) {

                Layer layer = new Layer(drawable.z);
                layer.drawables.add(drawable);

                boolean isLast = true;

                // Place layer to correct position in the list
                for (int listIndex = 0; listIndex < layers.size();
                     listIndex++) {

                    if (layers.get(listIndex).z > layer.z) {
                        layers.add(listIndex, layer);
                        isLast = false;
                        break;
                    }
                }

                if (isLast) {
                    layers.add(layer);
                }
            }
        } else {
            Layer layer = new Layer(drawable.z);
            layer.drawables.add(drawable);
            layers.add(layer);
        }
    }

    /**
     * Sets background color to given color.
     *
     * @param color New background color
     */
    public void setBackgroundColor(Color color) {

        background = color;
    }

    /**
     * Removes GameObject from lists.
     *
     * @param gameObject GameObject to remove
     */
    public void deleteGameObject(GameObject gameObject) {

        for (Layer layer : layers) {

            ArrayList<DrawableObject> toRemove = new ArrayList<>();

            for (DrawableObject drawable : layer.drawables) {
                if (drawable.gameObject == gameObject) {
                    toRemove.add(drawable);
                }
            }

            for (DrawableObject drawable : toRemove) {
                layer.drawables.remove(drawable);
            }
        }
    }
}
