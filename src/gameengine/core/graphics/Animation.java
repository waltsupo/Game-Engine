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
    public int[][] images;
    public Image source;
    public int width;
    public int height;
    public boolean loop;
    public float time;

    private Animation(Image src, int[][] coords, int width, int height, float time) {

        source = src;
        this.images = coords;
        this.width = width;
        this.height = height;
        this.time = time;
        loop = true;
    }

    public static Animation createAnimation(Image src, int startX, int startY, int width, int height, int images, float time) {

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

        return new Animation(src, coords, width, height, time);
    }
}
