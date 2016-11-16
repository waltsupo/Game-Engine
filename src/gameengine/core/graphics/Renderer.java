package gameengine.core.graphics;

import gameengine.core.*;
import gameengine.core.components.*;
import gameengine.core.components.Component;
import gameengine.core.components.enums.ShapeType;
import gameengine.core.components.enums.Shapes;
import gameengine.core.Window;
import gameengine.mathlib.*;
import gameengine.mathlib.Rectangle;
import gameengine.utils.tiled.TileSet;
import gameengine.utils.tiled.TiledMap;
import gameengine.utils.tiled.TiledMapLayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Handles Drawing.
 *
 * Draws all objects that have required components to the screen.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1002
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
     * Layers to draw
     */
    private ArrayList<Layer> layers;

    /**
     * Background color, default black
     */
    private Color background;

    /**
     * Defines values and starts listening for component information.
     */
    public Renderer() {

        window = GameManager.getGame().getWindow();
        layers = new ArrayList<>();
        camera = new Camera();
        background = Color.BLACK;

        // Listen component changes
        ComponentManager.addCombination(this, ShapeRenderer.class,
                Transform.class);
        ComponentManager.addCombination(this, SpriteRenderer.class,
                Transform.class);
        ComponentManager.addCombination(this, TiledMapRenderer.class);
    }

    /**
     * Draws to screen.
     */
    public void render() {

        // create and get graphics

        g = window.start();

        // Background color
        g.setColor(background);
        g.fillRect(0,0,GameManager.getWidth(), GameManager.getHeight());

        g.translate(-camera.transform.x, -camera.transform.y);

        // Draw layers
        for (Layer layer : layers) {
            for (DrawableObject drawable : layer.drawables) {

                drawable.draw(g, camera);
            }
        }

        g.scale(1, -1);

        // Update window
        window.stop();
    }

    /**
     * Returns current camera
     *
     * @return Camera used by renderer
     */
    public Camera getCamera() {

        return camera;
    }

    /**
     * Adds DrawableObject to correct layer to be drawn
     *
     * @param gameObject GameObject that has required components
     * @param component Component that was created
     */
    public void newComponent(GameObject gameObject, Component component) {


        if (component instanceof SpriteRenderer) {

            addDrawable(new DrawableSprite(gameObject.transform,
                    (SpriteRenderer)component));
        } else if (component instanceof ShapeRenderer) {

            addDrawable(new DrawableShape(gameObject.transform,
                    (ShapeRenderer)component));
        } else if (component instanceof TiledMapRenderer) {

            TiledMap map = ((TiledMapRenderer) component).tilemap;

            for (TiledMapLayer layer : map.layers) {
                addDrawable(new DrawableTiledLayer(
                        (TiledMapRenderer) component, layer));
            }
        }
    }

    /**
     * Adds drawable to correct layer
     *
     * @param drawable Drawable to add
     */
    private void addDrawable(DrawableObject drawable) {

        boolean found = false;

        // If has layers, else create new one
        if (layers.size() != 0) {

            for (Layer layer : layers) {

                // If layer with same Z-index is found
                if (layer.z == drawable.layer) {

                    layer.drawables.add(drawable);
                    found = true;
                    break;
                }
            }

            // If no layer with same Z-index, create new layer
            if (!found) {

                Layer layer = new Layer(drawable.layer);
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
            Layer layer = new Layer(drawable.layer);
            layer.drawables.add(drawable);
            layers.add(layer);
        }
    }

    /**
     * Sets background color to given color
     *
     * @param color New background color
     */
    public void setBackgroundColor(Color color) {

        background = color;
    }

    /**
     * Removes GameObject from the list.
     *
     * @param gameObject GameObject that no longer has required components
     * @param first First class on combination list, used to separate different
     *              combination on one listener
     */
    @Override
    public void removeComponent(GameObject gameObject, Class first) {

        // TODO remove component
        /*if (first == ShapeRenderer.class) {
            shapesToDraw.remove(gameObject);
        } else if (first == SpriteRenderer.class) {
            spritesToDraw.remove(gameObject);
        }*/
    }

    /**
     * Removes GameObject from lists.
     *
     * @param gameObject gameobject to remove
     */
    public void deleteGameObject(GameObject gameObject) {

        // TODO delete gameobject
        //shapesToDraw.remove(gameObject);
        //spritesToDraw.remove(gameObject);
    }
}