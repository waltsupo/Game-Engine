package gameengine.core.components;


import gameengine.utils.tiled.TiledMap;

/**
 * Component that draws tiled map
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class TiledMapRenderer extends Component{

    /**
     * TiledMap to draw
     */
    public TiledMap tilemap;

    /**
     * Constructor
     *
     * @param map Map to draw
     */
    public TiledMapRenderer(TiledMap map) {

        tilemap = map;
    }
}
