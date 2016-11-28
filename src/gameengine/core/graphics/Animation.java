package gameengine.core.graphics;

import java.awt.*;

/**
 * Group of images to show, changing after specific time.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1128
 * @since 1.7
 **/
public class Animation {

    /**
     * Bottom-left corner coordinates for all images in src image.
     */
    public int[][] images;

    /**
     * Source image for animation.
     */
    public Image source;

    /**
     * Width of each animation image.
     */
    public int width;

    /**
     * Height of each animation image.
     */
    public int height;

    /**
     * Width of animation image in src image.
     */
    public int imageWidth;

    /**
     * Height of animation image in src image.
     */
    public int imageHeight;

    /**
     * Bottom-left corner's distance from GameObject in X-axis.
     */
    public int offsetX;

    /**
     * Bottom-left corner's distance from GameObject in Y-axis.
     */
    public int offsetY;

    /**
     * Should animation loop or stay on the last frame.
     */
    public boolean loop;

    /**
     * How long one image should be drawn.
     */
    public float time;

    /**
     * Index of current image.
     */
    public int index;

    /**
     * Is animation finished.
     */
    public boolean isFinished;

    /**
     * Flip on X-axis.
     */
    private boolean originalX;

    /**
     * Flip on Y-axis.
     */
    private boolean originalY;

    /**
     * Defines values for animation.
     *
     * @param src Source image
     * @param coords Array of coordinates of bottom-left corners
     * @param width Width of wanted animation images
     * @param height Height of wanted animation images
     * @param imageWidth Width of animation image on src image
     * @param imageHeight Height of animation image on src image
     * @param time Time between animation images
     */
    private Animation(Image src, int[][] coords, int width, int height, int imageWidth, int imageHeight, float time) {

        source = src;
        this.images = coords;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.time = time;
        offsetX = 0;
        offsetY = 0;
        loop = true;
        this.width = width;
        this.height = height;
        index = 0;
        isFinished = false;
        originalX = true;
        originalY = true;
    }

    /**
     * Moves animation to start.
     */
    public void play() {

        index = 0;
        isFinished = false;
    }

    /**
     * Flips animation on X-axis.
     *
     * @param originalDir Should animation be drawn as it is in src
     */
    public void flipX(boolean originalDir) {

        if (!(originalX == originalDir)) {
            originalX = originalDir;

            for (int index = 0; index < images.length; index++) {
                images[index][0] += imageWidth;
            }
            imageWidth = -imageWidth;
        }
    }

    /**
     * Flips animation on Y-axis.
     *
     * @param originalDir Should animation be drawn as it is in src
     */
    public void flipY(boolean originalDir) {

        if (!(originalY == originalDir)) {
            originalY = originalDir;
        }

        for (int index = 0; index < images.length; index++) {
            images[index][1] += imageHeight;
        }
        imageHeight = -imageHeight;
    }

    /**
     * Creates new animation.
     *
     * @param src Source image
     * @param width Width of wanted animation images
     * @param height Height of wanted animation images
     * @param startX Starting X-coordinate on src image (bottom-left corner)
     * @param startY Starting Y-coordinate on src image (bottom-left corner)
     * @param imageWidth Width of the animation image in src
     * @param imageHeight Height of the animation image in src
     * @param images How many images animation has
     * @param time Time between animation images
     * @return New animation created from given parameters
     */
    public static Animation createAnimation(Image src, int width, int height, int startX, int startY, int imageWidth, int imageHeight, int images, float time) {

        int[][] coords = new int[images][2];
        int x = startX;
        int y = startY;


        for (int index = 0; index < coords.length; index++) {

            if (x + imageWidth > src.getWidth(null)) {
                y += imageHeight;
                x = 0;
            }

            coords[index][0] = x;
            coords[index][1] = y;
            x += imageWidth;
        }

        return new Animation(src, coords, width, height, imageWidth, imageHeight, time);
    }
}
