package gameengine.core;

/**
 * Allows Game and Scene related tasks to be done anywhere.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class GameManager {

    /**
     * Scene currently in use.
     */
    private static Game game;

    /**
     * Width of content part of the window.
     */
    private static int width;

    /**
     * Height of content part of the window.
     */
    private static int height;

    /**
     * Gets width of the game.
     *
     * @return Width of the content part of the window
     */
    public static int getWidth() {

        return width;
    }

    /**
     * Sets width of the game.
     *
     * @param width New width
     */
    public static void setWidth(int width) {

        if (width > 0) {
            GameManager.width = width;
        }
    }

    /**
     * Gets width of the game.
     *
     * @return Height of the content part of the window
     */
    public static int getHeight() {

        return height;
    }

    /**
     * Sets height of the window.
     *
     * @param height New height
     */
    public static void setHeight(int height) {

        if (height > 0) {
            GameManager.height = height;
        }
    }

    /**
     * Sets value for game.
     *
     * @param newGame New Game
     */
    static void setGame(Game newGame) {

        if (newGame != null) {

            game = newGame;
        }
    }

    /**
     * Gets game.
     *
     * @return Game
     */
    public static Game getGame() {

        return game;
    }

    /**
     * Changes Game's Scene.
     * 
     * @param newScene New Scene to update
     */
    public static void setScene(Scene newScene) {

        game.setScene(newScene);
    }

    /**
     * Removes references to given GameObject from Scene.
     *
     * @param gameObject GameObject to remove
     */
    public static void removeGameObject(GameObject gameObject) {

        game.getScene().removeGameObject(gameObject);
    }

    /**
     * Ends game.
     */
    public static void endGame() {

        game.endGame();
        System.exit(1);
    }
}
