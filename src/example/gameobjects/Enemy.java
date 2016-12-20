package example.gameobjects;

import example.components.Health;
import gameengine.core.GameObject;
import gameengine.core.components.Collider;
import gameengine.core.components.SpriteRenderer;
import gameengine.core.components.Transform;
import gameengine.mathlib.Rectangle;
import gameengine.mathlib.Vector;
import gameengine.utils.Files;

import java.io.IOException;

/**
 * Enemy for the game.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1219
 * @since 1.7
 */
public class Enemy extends GameObject {

    /**
     * SpriteRenderer for Enemy.
     */
    private SpriteRenderer spriteRenderer;

    /**
     * Enemy's Collider.
     */
    private Collider collider;

    /**
     * Health of the Enemy.
     */
    private Health health;

    /**
     * How fast Enemy moves.
     */
    private float speed;

    /**
     * Defines starting values.
     *
     * @param x Position on X-axis
     * @param y Position on Y-axis
     */
    public Enemy(float x, float y) {

        transform.x = x;
        transform.y = y;
        transform.width = 50;
        transform.height = 50;

        speed = 100;

        try {
            spriteRenderer = new SpriteRenderer(
                    Files.loadImage("src/example/assets/enemy.png", 50, 50), 3);
            addComponent(spriteRenderer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collider = new Collider(new Rectangle(x, y, 50, 50), 2);
        addComponent(collider);

        health = new Health(1);
        addComponent(health);
    }

    /**
     * Updates position.
     *
     * @param delta How much time has passed since last update
     * @param player Player to follow
     */
    public void update(float delta, Transform player) {

        Vector distance = new Vector(player.x - transform.x,
                player.y - transform.y).getUnit();

        translate(distance.x * speed * delta, distance.y * speed * delta);
    }

    /**
     * Resolves collisions.
     *
     * @param col Other Collider
     * @return True if solves collision between objects
     */
    @Override
    public boolean collision(Collider col) {

        if (col.layer == 4) {
            health.amount--;
            return true;
        }

        Rectangle temp = (Rectangle) col.shape;

        float pointX = 0;
        float pointY = 0;

        float dista = (transform.x + transform.width - temp.x) * (transform.x
                + transform.width - temp.x);
        float distb = (transform.x - temp.x - temp.width) * (transform.x -
                temp.x  - temp.width);
        float distx = 0;
        float disty = 0;

        if (dista < distb) {
            pointX = temp.x - transform.width;
            distx = dista;
        } else {
            pointX = temp.x + temp.width;
            distx = distb;
        }

        dista = (transform.y + transform.height - temp.y) * (transform.y +
                transform.height - temp.y);
        distb = (transform.y - temp.y - temp.height) * (transform.y - temp.y
                - temp.height);

        if (dista < distb) {
            pointY = temp.y - transform.height;
            disty = dista;
        } else {
            pointY = temp.y + temp.height;
            disty = distb;
        }

        if (distx < disty) {
            moveTo(pointX, transform.y);
        } else {
            moveTo(transform.x, pointY);
        }

        return false;
    }

    /**
     * Moves GameObject to given location.
     *
     * @param x New x-coordinate
     * @param y New y-coordinate
     */
    @Override
    public void moveTo(float x, float y) {

        super.moveTo(x, y);
        collider.shape.moveTo(x, y);
    }

    /**
     * Moves GameObject by given amount.
     *
     * @param x Change on x-axis
     * @param y Change on y-axis
     */
    @Override
    public void translate(float x, float y) {

        super.translate(x, y);
        collider.shape.translate(x, y);
    }
}
