package example;

import gameengine.core.Game;
import gameengine.utils.Config;
import example.scenes.GameScene;
import gameengine.utils.Files;
import gameengine.utils.tiled.Maps;

/**
 * Main object for the game.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1219
 * @since 1.7
 */
public class AlphaGame extends Game {

    /**
     * Sets up values.
     *
     * @param config Config-object that contains window size
     */
    public AlphaGame(Config config) {
        super(config);
    }

    /**
     * Sets game specific settings.
     */
    public void start() {

        Files.defaultPath = "res/";
        Maps.defaultPath = "res/";
        setScene(new GameScene());
    }
}
