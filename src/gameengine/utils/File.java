package gameengine.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private HashMap < String, String > pairs;

    /**
     * Path to current file.
     */
    private Path path;

    /**
     * Default constructor.
     */
    public File() {

        pairs = new HashMap<>();
        path = null;
    }

    /**
     * Gets value of given.
     *
     * @param key Key to get value from
     * @return Value of the key
     */
    public String get(String key) {

        return pairs.get(key);
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
     * Loads current file.
     */
    public void load() throws IOException {

        pairs.clear();

        List<String> lines = java.nio.file.Files.readAllLines(
                path, Charset.defaultCharset());

        for (String line : lines) {

            String key = null;
            String value = null;
            String string = "";
            boolean asKey = true;

            for (int charI = 0; charI < line.length(); charI++) {

                if (charI == line.length() - 1) {
                    string += line.charAt(charI);
                    value = string;
                    string = "";
                } else if (line.charAt(charI) == ':') {
                    asKey = false;
                    key = string;
                    string = "";
                } else {
                    string += line.charAt(charI);
                }
            }

            if (key != null && value != null) {

                pairs.put(key, value);
            }
        }
    }

    /**
     * Loads file located at given path.
     *
     * @param url Path to file
     */
    public void load(String url) throws IOException {

        if (url != null && !url.equals("")) {
            path = FileSystems.getDefault().getPath(url);
            load();
        }
    }

    /**
     * Writes to current file.
     */
    public void save() throws IOException {

        String toSave = "";

        for (Map.Entry < String, String > entry : pairs.entrySet()) {

            toSave += entry.getKey();
            toSave += ":";
            toSave += entry.getValue();
            toSave += "\n";
        }

        java.nio.file.Files.write(path, toSave.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Writes to file located at given path.
     *
     * @param url Path to file
     */
    public void save(String url) throws IOException {

        if (url != null && !url.equals("")) {
            path = FileSystems.getDefault().getPath(url);
            save();
        }
    }
}
