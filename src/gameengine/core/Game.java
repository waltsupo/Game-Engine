package gameengine.core;

import gameengine.core.physics.Physics;
import gameengine.utils.Config;
import gameengine.utils.Timers;

/**
 * Main system to run the game.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public abstract class Game {

    /**
     * Current Scene.
     *
     * Used on update/rendering.
     */
    private Scene currentScene;

    /**
     * Window for the application.
     */
    private Window window;

    /**
     * Is game alive or not.
     */
    private boolean gameOn;

    /**
     * Max frames per second, restricts rendering.
     */
    protected int frameCap;

    /**
     * Max updates per second.
     */
    protected int updateCap;

    /**
     * Frames per second.
     */
    private int frames;

    /**
     * Constructor, Starts the game loop.
     *
     * @param config Settings for the game
     */
    public Game(Config config) {

        System.setProperty("sun.java2d.opengl",
                Boolean.toString(config.openGL));

        // Settings
        setup(config);
        start();

        if (currentScene == null) {

            // Create empty scene
            currentScene = new Scene() {

                /**
                 * Updates scene.
                 *
                 * @param delta Elapsed time since last update
                 */
                public void update(float delta) {}
            };
        }

        // Start game
        gameLoop();
    }

    /**
     * Defines few variables and creates new objects.
     *
     * @param config Settings for the game
     */
    private void setup(Config config) {

        // Set up static classes
        GameManager.setGame(this);
        GameManager.setWidth(config.width);
        GameManager.setHeight(config.height);
        Timers.setup();

        window = new Window();
        frameCap = 60;
        updateCap = 60;
        gameOn = true;
    }

    /**
     * Sets user settings before game loop starts.
     */
    abstract public void start();

    /**
     * Runs the game.
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

        while (gameOn) {

            endTime = System.nanoTime();
            delta = (endTime - startTime) / 1000000000.0;
            startTime = endTime;

            // Add passed time to counters
            renderTime += delta;
            updateTime += delta;
            second += delta;

            update((float) delta);
            render((float) delta);

            if (second >= 1) {
                System.out.println(frames);
                second--;
                frames = 0;
            }

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
        }
    }

    /**
     * Updates the game, for example collisions and calls scene's update.
     *
     * @param delta Elapsed time since last update
     */
    private void update(float delta) {

        currentScene.updateScene(delta);
        Timers.updateTimers(delta);
    }

    /**
     * Renders game to window.
     *
     * @param delta Elapsed time since last update
     */
    private void render(float delta) {

        currentScene.render(delta);
        frames++;
    }

    /**
     * Ends game loop.
     */
    public void endGame() {

        gameOn = false;
    }

    /**
     * Changes current scene.
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
     * Returns current scene.
     *
     * @return Current Scene
     */
    public Scene getScene() {

        return currentScene;
    }

    /**
     * Returns current window.
     *
     * @return window used in the game
     */
    public Window getWindow() {

        return window;
    }
}
