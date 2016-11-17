package gameengine.core.components;

import gameengine.core.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles adding components.
 *
 * Has list containing listeners and wanted combination, notifies
 * listeners GameObject has required components.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class ComponentManager {

    /**
     * Contains listener and list of classes(component combinations).
     */
    private static HashMap<ComponentListener,
            ArrayList<ArrayList<Class>>> combinations = new HashMap<>();

    /**
     * Adds new combination to list.
     *
     * Listener calls method to get information about new components.
     *
     * @param listener ComponentListener that wants information
     * @param components components to listen at
     */
    public static void addCombination(ComponentListener listener,
                                      Class... components) {

        ArrayList<Class> temp = new ArrayList<>();

        // Add given components to ArrayList
        for (Class compClass : components) {
            temp.add(compClass);
        }

        // If already listening combination
        if (combinations.containsKey(listener)) {
            combinations.get(listener).add(temp);
        } else {
            ArrayList<ArrayList<Class>> list = new ArrayList<>();
            list.add(temp);
            combinations.put(listener, list);
        }
    }

    /**
     * Stops listener from getting information about combination of components.
     *
     * @param listener Listener that does not need information anymore
     * @param first Class of the first component on the list
     */
    public static void removeCombination(ComponentListener listener,
                                         Class first) {

        // If has listener
        if (combinations.containsKey(listener)) {

            // If combination has first as first element, remove combination
            for (ArrayList<Class> classList : combinations.get(listener)) {

                if (classList.get(0) == first) {

                    combinations.get(listener).remove(classList);
                    break;
                }
            }
        }
    }

    /**
     * Looks if any listener wants information about new component.
     *
     * @param gameObject Component's parent GameObject
     * @param component New component
     */
    public static void newComponent(GameObject gameObject,
                                    Component component) {

        // Loop every entry in combinations
        for (Map.Entry<ComponentListener,
                ArrayList<ArrayList<Class>>> entry : combinations.entrySet()) {

            // Loop class combinations
            for (int index = 0; index < entry.getValue().size(); index++) {
                ArrayList<Class> classList = entry.getValue().get(index);

                // If contains new component's class
                if (classList.contains(component.getClass())) {

                    boolean hasComponents = true;

                    // See if GameObject has other components required
                    for (Class compClass : classList) {

                        if (gameObject.getComponent(compClass) == null) {
                            hasComponents = false;
                        }
                    }

                    if (hasComponents) {
                        entry.getKey()
                                .newGameObject(gameObject, classList.get(0));
                    }
                }
            }
        }
    }
}
