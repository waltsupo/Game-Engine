package gameengine.core;

import gameengine.core.physics.Physics;
import gameengine.utils.Config;

/**
 * Main system to run the game
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public abstract class Game {

    /**
     * Current Scene
     *
     * Used on update/rendering
     */
    private Scene currentScene;

    /**
     * Window for the application
     */
    private Window window;

    /**
     * Is game alive or not
     */
    private boolean gameOn;

    /**
     * Max frames per second, restricts rendering
     */
    protected int frameCap;

    /**
     * Max updates per second
     */
    protected int updateCap;

    /**
     * Frames per second
     */
    private int frames;

    /**
     * Constructor, Starts the game loop
     */
    public Game(Config config) {

        System.setProperty("sun.java2d.opengl",
                Boolean.toString(config.openGL));

        // Settings
        setup(config);
        start();

        if (currentScene == null) {
            currentScene = new Scene() {
                public void update(float delta) {}
            };
        }

        // Start game
        gameLoop();
    }

    /**
     * Defines few variables and creates new objects
     */
    private void setup(Config config) {

        // Set up static classes
        GameManager.setGame(this);
        GameManager.setWidth(config.width);
        GameManager.setHeight(config.height);

        window = new Window();
        frameCap = 60;
        updateCap = 60;
        gameOn = true;
    }

    /**
     * Sets user settings before game loop starts
     */
    abstract public void start();

    /**
     * Runs the game
     */
    private void gameLoop() {

        long startTime = System.nanoTime();
        long endTime = 0;

        double delta = 0;

        // Time since last render
        double renderTime = 0;
        // Time since last update
        double updateTime = 0;
        // How much longer it took to update compared to updateCap
        double overUpdate = 0;

        double second = 0;
        frames = 0;

        while(gameOn) {

            endTime = System.nanoTime();
            delta = (endTime - startTime) / 1000000000.0;
            startTime = endTime;

            // Add passed time to counters
            renderTime += delta;
            updateTime += delta;
            second += delta;

            update((float) delta);
            render();

            /*// Update if enough time has passed
            if (updateTime >= 1f/updateCap) {

                Time.delta = updateTime;
                updateTime = 0;
                update();
            }

            // graphics if enough time has passed
            if (renderTime >= 1f/frameCap) {

                renderTime = 0;
                render();
            }*/

            if (second >= 1) {
                second--;
                System.out.println("framerate: " + frames);
                frames = 0;
            }
        }
    }

    /**
     * Updates the game, for example collisions and calls scene's update
     */
    private void update(float delta) {


        currentScene.updateScene(delta);
    }

    /**
     * Renders game to window
     */
    private void render() {

        currentScene.render();
        frames++;
    }

    /**
     * Ends game loop
     */
    public void endGame() {

        gameOn = false;
    }

    /**
     * Changes current scene
     *
     * @param scene New Scene
     */
    public void setScene(Scene scene) {

        if (scene != null) {

            currentScene = scene;

            // Static classes
            Physics.setScene(scene);
        }
    }

    /**
     * Returns current scene
     *
     * @return Current Scene
     */
    public Scene getScene() {

        return currentScene;
    }

    /**
     * Returns current window
     *
     * @return window used in the game
     */
    public Window getWindow() {

        return window;
    }
}
