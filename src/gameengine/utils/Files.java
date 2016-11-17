package gameengine.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Contains file loading methods
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1117
 * @since 1.7
 **/
public class Files {

    /**
     * Starting folder
     */
    public static String defaultPath = "src/assets";

    /**
     * Creates compatible image from the source
     *
     * @param url Path to image
     * @return Compatible image
     * @throws IOException If error getting image
     */
    public static Image loadImage(String url) throws IOException {

        Image source = ImageIO.read(new File(url));

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        // Create compatible image
        Image image = config.createCompatibleImage(
                source.getWidth(null),
                source.getHeight(null),
                Transparency.TRANSLUCENT);

        Graphics g = image.getGraphics();
        g.drawImage(source, 0, 0,
                source.getWidth(null), source.getHeight(null), null);
        g.dispose();

        return image;
    }

    /**
     * Creates compatible image from the source and returns it scaled
     *
     * @param url Path from default path to the image
     * @param width Width of the image to return
     * @param height Height of the image to return
     * @return Image from the source
     * @throws IOException If error getting image
     */
    public static Image loadImage(String url, int width, int height) throws IOException {


        Image source = ImageIO.read(new File(url));

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        // Create compatible image
        Image image = config.createCompatibleImage(
                width,
                height,
                Transparency.TRANSLUCENT);

        Graphics g = image.getGraphics();
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();

        return image;
    }
}
