package example.components;

import gameengine.core.components.Component;

/**
 * Contains information about the health of the GameObject.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1220
 * @since 1.7
 **/
public class Health extends Component {

    /**
     * How many health points left.
     */
    public int amount;

    /**
     * Defines setup values.
     *
     * @param amount How many health points
     */
    public Health(int amount) {

        this.amount = amount;
    }
}
