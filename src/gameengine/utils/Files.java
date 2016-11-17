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
 * @version 2016.1711
 * @since 1.7
 **/
public class Files {

    /**
     * Starting folder
     */
    public static String defaultPath = "src/assets";

    /**
     * Creates compatible image from the source and returns it
     *
     * @param url Path from default path to the image
     * @param width Width of the image to return
     * @param height Height of the image to return
     * @return Image from the source
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
