package example;

import gameengine.utils.Config;

/**
 * Launcher for the game.
 *
 * Defines values like window length and height, and starts the game.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1220
 * @since 1.7
 */
public class launcher {

    /**
     * Starts the game.
     *
     * @param args Not used
     */
    public static void main(String[] args) {

        Config config = new Config(1024, 600);
        config.openGL = false;
        System.out.println(System.getProperty("user.dir"));

        new AlphaGame(config);
    }
}
