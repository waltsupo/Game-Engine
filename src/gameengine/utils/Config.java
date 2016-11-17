package gameengine.utils;

/**
 * Object that contains information about game's properties
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Config {

    /**
     * Width of the game window
     */
    public int width;

    /**
     * Height of the game window
     */
    public int height;

    /**
     * Should openGl be used or not
     */
    public boolean openGL;

    /**
     * Default constructor
     */
    public Config() {

        width = 600;
        height = 800;

        openGL = true;
    }

    /**
     * Constuctor with width and height for the game
     *
     * @param width Width of the game window
     * @param height Height of the game window
     */
    public Config(int width, int height) {

        this.width = width;
        this.height = height;

        openGL = true;
    }
}
