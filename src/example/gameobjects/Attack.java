package example.gameobjects;

import gameengine.core.GameObject;
import gameengine.core.components.Collider;
import gameengine.mathlib.Rectangle;

/**
 * Melee attack used by the player.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1220
 * @since 1.7
 **/
public class Attack extends GameObject {

    /**
     * Attack's Collider.
     */
    public Collider collider;

    /**
     * Width of the attack.
     */
    public int width;

    /**
     * Constructor for Attack.
     */
    public Attack() {

        transform.x = 0;
        transform.y = 0;
        transform.width = 50;
        width = 50;
        transform.height = 100;

        collider = new Collider(new Rectangle(0, 0, 50, 100), 4);
        addComponent(collider);
    }

    /**
     * Moves attack-object to given position.
     *
     * @param x New x-coordinate
     * @param y New y-coordinate
     */
    @Override
    public void moveTo(float x, float y) {

        super.moveTo(x, y);
        collider.shape.moveTo(x, y);
    }
}
