package gameengine.utils.tiled;

import java.util.ArrayList;

/**
 * TiledMap object group.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1117
 * @since 1.7
 **/
public class TiledObjectGroup {

    /**
     * Name of the group.
     */
    public String name;

    /**
     * ArrayList of objects.
     */
    public ArrayList<TiledObject> objects;

    /**
     * Default constructor.
     */
    public TiledObjectGroup() {

        name = "";
        objects = new ArrayList<>();
    }
}
