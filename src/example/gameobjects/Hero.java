package example.gameobjects;

import gameengine.core.components.AnimatedSprite;
import gameengine.core.components.Collider;
import gameengine.core.GameObject;
import gameengine.core.graphics.Animation;
import gameengine.core.input.InputManager;
import gameengine.mathlib.Rectangle;
import gameengine.mathlib.Vector;
import gameengine.utils.Files;
import gameengine.core.components.Velocity;

import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Player controlled object.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1219
 * @since 1.7
 */
public class Hero extends GameObject{

    /**
     * Draws animation for the player.
     */
    private AnimatedSprite animatedSprite;

    /**
     * Player's Collider.
     */
    private Collider collider;

    /**
     * Gravity for player.
     */
    public Velocity velocity;

    /**
     * Player's current animation if not on air.
     */
    private Animation movingAnimation;

    /**
     * Player's idle animation.
     */
    private Animation idle;

    /**
     * Player's running animation.
     */
    private Animation run;

    /**
     * Player's jump animation.
     */
    private Animation jump;

    /**
     * Player's attack animation.
     */
    private Animation attack;

    /**
     * Can player attack.
     */
    private boolean canAttack;

    /**
     * Is player facing right.
     */
    private boolean facingRight;

    /**
     * Is player running.
     */
    private boolean running;

    /**
     * Attack for player to use.
     */
    private Attack att;

    /**
     * Vector that describes player's move at current frame.
     */
    private Vector move;

    /**
     * Defines values.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param width Width of the object
     * @param height Height of the object
     */
    public Hero(int x, int y, int width, int height) {

        transform.x = x;
        transform.y = y;
        transform.width = width;
        transform.height = height;
        canAttack = true;
        facingRight = true;
        running = false;

        try {
            idle = Animation.createAnimation(
                    Files.loadImage("src/example/assets/idle.png"),
                    50,95,0,0,232,439,10,0.05f);
            run = Animation.createAnimation(
                    Files.loadImage("src/example/assets/run.png"),
                    78,95,0,0,363,458,10,0.05f);
            jump = Animation.createAnimation(
                    Files.loadImage("src/example/assets/jump.png"),
                    78,105,0,0,362,483,10,0.05f);
            jump.loop = false;
            attack = Animation.createAnimation(
                    Files.loadImage("src/example/assets/attack.png"),
                    115,107,0,0,536,455,10,0.05f);
            attack.loop = false;
            movingAnimation = idle;
        } catch (IOException e) {
            e.printStackTrace();
        }

        animatedSprite = new AnimatedSprite(idle, 3);
        addComponent(animatedSprite);

        collider = new Collider(new Rectangle(transform.x, transform.y,
                transform.width, transform.height), 1);
        addComponent(collider);

        velocity = new Velocity(0, 0, 0, -500);
        addComponent(velocity);

        move = new Vector(0, 0);
        att = new Attack();
        att.collider.active = false;
    }

    /**
     * Updates player's position and animation.
     *
     * @param delta Time since last update.
     */
    public void update(float delta) {


        if (InputManager.isKeyDown(KeyEvent.VK_W) && !velocity.onAir) {
            velocity.velocity.y = 250;
            jump.play();
            animatedSprite.animation = jump;
        }

        move.x = 0;

        if (InputManager.isKeyDown(KeyEvent.VK_A)) {
            move.x -= 250;
            facingRight = false;
            attack.offsetX = -65;
        }

        if (InputManager.isKeyDown(KeyEvent.VK_D)) {
            move.x += 250;
            facingRight = true;
            attack.offsetX = 0;
        }

        if (canAttack) {
            if (InputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                animatedSprite.animation = attack;
                attack.play();
                canAttack = false;

                if (facingRight) {
                    att.collider.shape.moveTo(transform.x + transform.width,
                            transform.y);
                } else {
                    att.collider.shape.moveTo(transform.x - att.width,
                            transform.y);
                }

                att.collider.active = true;
            }
        } else {
            att.collider.active = false;
            if (animatedSprite.animation != attack || attack.isFinished) {
                canAttack = true;
                movingAnimation.play();
                animatedSprite.animation = movingAnimation;
            }
        }

        if (move.x == 0) {
            if (running) {
                running = false;
                run.index = 0;
                movingAnimation = idle;
            }
        } else {
            if (!running) {
                running = true;
                idle.index = 0;
                movingAnimation = run;
            }
        }

        if (velocity.onAir && animatedSprite.animation != jump) {

            if (velocity.velocity.y <= 0) {
                jump.index = jump.images.length - 1;
                animatedSprite.animation = jump;
            } else {
                jump.play();
                animatedSprite.animation = jump;
            }
        }

        if (movingAnimation != animatedSprite.animation) {
            if (canAttack && !velocity.onAir) {
                animatedSprite.animation = movingAnimation;
            }
        }

        translate(move.x * delta, 0);
        animatedSprite.animation.flipX(facingRight);
    }

    /**
     * Activates attack.
     */
    private void attack() {

        Rectangle attack = new Rectangle();
        attack.width = 60;
        attack.height = 100;
        attack.y = transform.y;

        if (facingRight) {
            attack.x = transform.x + transform.width;
        } else {
            attack.x = transform.x - attack.width;
        }
    }

    /**
     * Resolves collisions.
     *
     * @param col Other Collider
     * @return True if solves collision completely
     */
    @Override
    public boolean collision(Collider col) {

        Rectangle temp = (Rectangle) col.shape;

        if (temp.y < transform.y) {
            velocity.onAir = false;
            velocity.velocity.y = 0;
        }

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
            velocity.velocity.y = 0;
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
     * Moves GameObject to given position.
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
