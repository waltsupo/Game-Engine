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
     * Default constructor
     */
    public File() {

        pairs = new HashMap<>();
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
}
