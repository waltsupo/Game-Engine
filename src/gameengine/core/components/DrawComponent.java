package gameengine.core.components;

/**
 * Parent class for drawing-related components.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1111
 * @since 1.7
 */
abstract class DrawComponent extends Component {

    /**
     * To what layer is this component drawn
     */
    public int layer;
}
