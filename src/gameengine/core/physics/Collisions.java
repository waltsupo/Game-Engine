package gameengine.core.physics;

import gameengine.core.SubSystem;
import gameengine.core.components.*;
import gameengine.core.GameObject;
import gameengine.mathlib.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles collisions.
 *
 * Has information on all Colliders, and methods to check collision
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1002
 * @since 1.7
 */
public class Collisions implements ComponentListener, SubSystem {

    /**
     * gameobjects that has required component to check collision.
     */
    private ArrayList<GameObject> collisionObjs;

    /**
     * Layer map, tells which layers ignore each other.
     */
    private HashMap<Integer, ArrayList<Integer>> layers;

    /**
     * Defines values and starts listening components.
     */
    public Collisions() {

        collisionObjs = new ArrayList<>();
        layers = new HashMap<>();
        ComponentManager.addCombination(this, Collider.class, Transform.class);
    }

    /**
     * Goes through every collider and looks if they collide with each other.
     */
    public void update() {

        for (int colIndex1 = 0; colIndex1 < collisionObjs.size(); colIndex1++) {
            Collider col1 = collisionObjs.get(colIndex1).
                    getComponent(Collider.class);

            for (int colIndex2 = colIndex1; colIndex2 < collisionObjs.size();
                 colIndex2++) {
                Collider col2 = collisionObjs.get(colIndex2).
                        getComponent(Collider.class);

                if (col1 != col2) {

                    if (checkCollision(col1, col2)) {

                        if (col1 != null && col2 != null
                                && collisionObjs.get(colIndex1) != null
                                && collisionObjs.get(colIndex2) != null) {

                            // If collision is not handled
                            if (!collisionObjs.get(colIndex1).collision(col2)){
                                collisionObjs.get(colIndex2).collision(col1);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if rectangle collides with any other collider.
     *
     * @param rect Rectangle to check collision for
     * @param layer Rectangle's layer
     * @return true if collides
     */
    public Boolean collides(Rectangle rect, int layer) {

        boolean collides = false;

        for (GameObject gameObject : collisionObjs) {
            Collider col = gameObject.getComponent(Collider.class);

            if (col.shape instanceof Rectangle
                    && layerCollision(layer, col.layer)) {

                if (rect.collides((Rectangle) col.shape)) {
                    collides = true;
                }
            }
        }

        return collides;
    }

    /**
     * Tells if layers collide.
     *
     * @param layer Layer
     * @param layer2 Layer
     * @return True if collides
     */
    private boolean layerCollision(int layer, int layer2) {

        boolean collides = true;

        if (layers.containsKey(layer)) {
            ArrayList<Integer> list = layers.get(layer);

            for (Integer layer3 : list) {

                if (layer3 == layer2) {
                    collides = false;
                    break;
                }
            }
        }

        return collides;
    }

    /**
     * Ignores collision between two layers.
     *
     * @param layer Layer
     * @param layer2 Layer
     */
    public void ignoreLayerCollision(int layer, int layer2) {

        if (layers.containsKey(layer)) {
            layers.get(layer).add(layer2);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(layer2);
            layers.put(layer, list);
        }

        if (layers.containsKey(layer2)) {
            layers.get(layer2).add(layer);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(layer);
            layers.put(layer2, list);
        }
    }

    /**
     * Checks collisions for collider.
     *
     * @param col collider to look collisions for
     * @return true if collides
     */
    public boolean checkCollisions(Collider col) {

        for (int index1 = 0; index1 < collisionObjs.size(); index1++) {
            Collider col2 = collisionObjs.get(index1).
                    getComponent(Collider.class);

            if (col != col2) {

                if (checkCollision(col, col2)) {

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks collision between two colliders.
     *
     * @param col1 Collider 1
     * @param col2 COllider 2
     * @return true if collides
     */
    public boolean checkCollision(Collider col1, Collider col2) {

        if (!layerCollision(col1.layer, col2.layer))
            return false;

        return col1.shape.collides(col2.shape);
    }

    /**
     * Adds gameobject to list to look collisions for.
     *
     * @param gameObject Gameobject that has required components
     * @param comClass Component class first on the combination list
     */
    @Override
    public void newGameObject(GameObject gameObject, Class comClass) {

        collisionObjs.add(gameObject);
    }

    // TODO
    /**
     * Removes gameobject from the list.
     *
     * @param gameObject Gameobject that no longer has required components
     * @param first First class on combination list, used to separate different
     *              combination on one listener
     */
    @Override
    public void removeComponent(GameObject gameObject, Class first) {

    }

    /**
     * Removes all references to object in this class.
     *
     * @param gameObject to remove
     */
    public void deleteGameObject(GameObject gameObject) {

        collisionObjs.remove(gameObject);
    }
}
