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

    /**
     * Writes to current file.
     */
    public void save() {

    }

    /**
     * Writes to file located at given path.
     *
     * @param url Path to file
     */
    public void save(String url) {

    }

    /**
     * Adds new key-value pair.
     *
     * If key exists, nothing happens.
     *
     * @param key New key
     * @param value New Value
     */
    public void add(String key, String value) {

    }

    /**
     * Modifies given key.
     *
     * If key doesn't exist, new key will be created.
     *
     * @param key Key to modify
     * @param value New value
     */
    public void modify(String key, String value) {

    }

    /**
     * Removes given key.
     *
     * @param key Key to remove
     */
    public void remove(String key) {

    }
}
