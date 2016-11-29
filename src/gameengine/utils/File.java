package gameengine.utils;

import java.util.HashMap;

/**
 * Contains data, can be saved to or loaded from external file.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1129
 * @since 1.7
 */
public class File {

    /**
     * HashMap that contains all key-value pairs from the file.
     */
    private HashMap<String, String> pairs;

    /**
     * Path to current file.
     */
    private String src;

    /**
     * Default constructor
     */
    public File() {

        pairs = new HashMap<>();
        src = "";
    }

    /**
     * Gets value of given key as string.
     *
     * @param key Key to get value from
     * @return Value as a String
     */
    public String getString(String key) {

        return null;
    }

    /**
     * Loads current file again.
     */
    public void load() {

    }

    /**
     * Loads file located at given path.
     *
     * @param url Path to file
     */
    public void load(String url) {

    }
}
