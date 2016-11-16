package gameengine.core;

import gameengine.core.components.Collider;
import gameengine.core.components.Component;
import gameengine.core.components.ComponentManager;
import gameengine.core.components.Transform;

import java.util.ArrayList;

/**
 * Class for all GameObject to extends.
 *
 * Contains values/methods common for every GameObject
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1002
 * @since 1.7
 */
public class GameObject {

    /**
     * List containing all components.
     */
    private ArrayList<Component> components;

    /**
     * Parent object.
     */
    private GameObject parent;

    /**
     * List containing all child objects.
     */
    private ArrayList<GameObject> childs;

    /**
     * Transform component.
     */
    public Transform transform;

    /**
     * Defines values, and creates transform component.
     */
    public GameObject() {

        components = new ArrayList<>();
        parent = null;
        childs = new ArrayList<>();

        transform = new Transform(0,0,0,0);
        addComponent(transform);
    }

    /**
     * Moves object and it's child objects to given position.
     *
     * @param x New x-coordinate
     * @param y New y-coordinate
     */
    public void moveTo(float x, float y) {

        transform.x = x;
        transform.y = y;

        for (GameObject gameObject : childs) {
            gameObject.moveTo(x, y);
        }
    }

    /**
     * Moves object and it's child objects by given amount.
     *
     * @param x Change on x-axis
     * @param y Change on y-axis
     */
    public void translate(float x, float y) {

        transform.x += x;
        transform.y += y;

        for (GameObject gameObject : childs) {
            gameObject.translate(x, y);
        }
    }

    /**
     * Sets parent object.
     *
     * @param parent New parent
     */
    public void setParent(GameObject parent) {

        if (parent != this && parent != null) {
            this.parent = parent;
            parent.addChild(this);
        }
    }

    /**
     * Adds child object.
     *
     * @param child New child
     */
    public void addChild(GameObject child) {

        if (!childs.contains(child)) {
            childs.add(child);
        }
    }

    /**
     * Removes child object.
     *
     * @param child Child to remove
     */
    public void removeChild(GameObject child) {

        childs.remove(child);
    }

    /**
     * Adds component to the GameObject
     *
     * @param component Component to add
     */
    public void addComponent(Component component) {

        Component comp = getComponent(component.getClass());

        if (comp == null) {

            components.add(component);
            ComponentManager.newComponent(this, component);
        }
    }

    /**
     * Removes component from the GameObject.
     *
     * @param comClass Component class to remove
     */
    public <T extends Component> void removeComponent(Class<T> comClass) {

        Component toRemove = getComponent(comClass);

        if (toRemove != null) {

            ComponentManager.removeComponent(this, comClass);
            components.remove(toRemove);
        }
    }

    /**
     * Returns component of given class.
     *
     * @param comClass component's class
     * @return component of given class
     */
    public <T extends Component> T getComponent(Class<T> comClass) {

        Component toReturn = null;

        for (Component comp : components) {

            if (comp.getClass() == comClass) {
                toReturn = comp;
                break;
            }
        }

        return comClass.cast(toReturn);
    }

    /**
     * Handles collision and returns if collision was handled.
     *
     * @param col Other Collider
     * @return If collision was handled
     */
    public boolean collision(Collider col) {

        return false;
    }

    /**
     * Deletes all references to this and to childs.
     */
    public void delete() {

        GameManager.deleteGameObject(this);

        parent = null;
        childs = null;
        transform = null;
        components = null;

        /*ArrayList<Component> remove = new ArrayList<>(components);

        for (Component component : remove) {
            removeComponent(component.getClass());
        }


        for (GameObject gameObject : childs) {
            gameObject.delete();
        }*/
    }
}
