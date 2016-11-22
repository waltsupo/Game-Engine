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
    public boolean loop;
    public float time;
    public int index;
    public boolean isFinished;
    private boolean originalX;
    private boolean originalY;

    private Animation(Image src, int[][] coords, int width, int height, float time, boolean loop) {

        source = src;
        this.images = coords;
        this.width = width;
        this.height = height;
        this.time = time;
        this.loop = loop;
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
                images[index][0] += width;
            }
            width = -width;
        }
    }

    public void flipY(boolean originalDir) {

        if (!(originalY == originalDir)) {
            originalY = originalDir;
        }

        for (int index = 0; index < images.length; index++) {
            images[index][1] += height;
        }
        height = -height;
    }

    public static Animation createAnimation(Image src, int startX, int startY, int width, int height, int images, float time, boolean loop) {

        int[][] coords = new int[images][2];
        int x = startX;
        int y = startY;


        for (int index = 0; index < coords.length; index++) {

            if (x + width > src.getWidth(null)) {
                y += height;
                x = 0;
            }

            coords[index][0] = x;
            coords[index][1] = y;
            x += width;
        }

        for (int index = 0; index < coords.length; index++) {

                System.out.println(coords[index][0] + " " + coords[index][1]);
        }

        return new Animation(src, coords, width, height, time, loop);
    }
}
