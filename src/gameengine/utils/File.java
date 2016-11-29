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
     * Gets value of given.
     *
     * @param key Key to get value from
     * @return Value of the key
     */
    public String get(String key) {

        return null;
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

        if (!pairs.containsKey(key)
                && value != null && !value.equals("")) {

            pairs.put(key, value);
        }
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

        if (value != null && !value.equals("")) {
            if (pairs.containsKey(key)) {
                pairs.replace(key, value);
            } else {
                pairs.put(key, value);
            }
        }
    }

    /**
     * Removes given key.
     *
     * @param key Key to remove
     */
    public void remove(String key) {

        pairs.remove(key);
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

}
