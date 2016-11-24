package gameengine.core.graphics;

import java.awt.*;

/**
 * TODO description
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version %I%, %G%
 **/
public class Animation {

    // Top left corner
    public int[][] images = {{0, 0}, {536 * 1, 0}, {536 * 2, 0}, {536 * 3, 0}, {536 * 4, 0}, {0, 495}, {536, 495}, {536 * 2, 495}, {536 * 3, 495}, {536 * 4, 495}};
    public Image source;
    public int width;
    public int height;
    public int imageWidth;
    public int imageHeight;
    public int offsetX;
    public int offsetY;
    public boolean loop;
    public float time;
    public int index;
    public boolean isFinished;
    private boolean originalX;
    private boolean originalY;

    private Animation(Image src, int[][] coords, int width, int height, int imageWidth, int imageHeight, float time, boolean loop) {

        source = src;
        this.images = coords;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.time = time;
        offsetX = 0;
        offsetY = 0;
        this.loop = loop;
        this.width = width;
        this.height = height;
        index = 0;
        isFinished = false;
        originalX = true;
        originalY = true;
    }

    public void play() {

        index = 0;
        isFinished = false;
    }

    public void flipX(boolean originalDir) {

        if (!(originalX == originalDir)) {
            originalX = originalDir;

            for (int index = 0; index < images.length; index++) {
                images[index][0] += imageWidth;
            }
            imageWidth = -imageWidth;
        }
    }

    public void flipY(boolean originalDir) {

        if (!(originalY == originalDir)) {
            originalY = originalDir;
        }

        for (int index = 0; index < images.length; index++) {
            images[index][1] += imageHeight;
        }
        imageHeight = -imageHeight;
    }

    public static Animation createAnimation(Image src, int width, int height, int startX, int startY, int imageWidth, int imageHeight, int images, float time, boolean loop) {

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

        return new Animation(src, coords, width, height, imageWidth, imageHeight, time, loop);
    }
}
