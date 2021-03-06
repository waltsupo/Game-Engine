package gameengine.core;

import gameengine.core.graphics.Camera;
import gameengine.core.input.InputManager;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

/**
 * Window for the game.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Window {

    /**
     * Frame for the game.
     */
    private Frame frame;

    /**
     * Canvas for drawing.
     */
    private Canvas canvas;

    /**
     * Image for drawing.
     */
    private BufferedImage image;

    /**
     * graphics for drawing.
     */
    private Graphics2D g;

    /**
     * BufferStrategy for drawing.
     */
    private BufferStrategy bStrat;

    /**
     * Camera used in drawing.
     */
    private Camera camera;

    /**
     * Creates window.
     */
    Window() {

        frame = new Frame();

        // Prevent resizing
        frame.setResizable(false);

        // Close program on "close"
        frame.addWindowListener(new WindowAdapter() {

                                /**
                                 * Closes window.
                                 *
                                 * @param we Window event
                                 */
                                public void windowClosing(WindowEvent we) {

                                    try {
                                        frame.dispose();
                                    } catch (Exception e) {

                                    }

                                    GameManager.endGame();
                                }
                            }

        );

        // Set canvas on the middle
        frame.setLayout(new BorderLayout());

        // Create Componenets
        // Canvas
        canvas = new Canvas();
        canvas.setBounds(0,0, GameManager.getWidth(), GameManager.getHeight());

        // Add components
        frame.add(canvas);

        // Make the frame and canvas same size
        frame.pack();

        // Window to the middle of the screen
        frame.setLocationRelativeTo(null);
        // Set window visible
        frame.setVisible(true);
        frame.setFocusable(true);

        InputManager input = new InputManager();
        canvas.addKeyListener(input);
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);
        canvas.setFocusable(true);

        // Create compatible image
        GraphicsEnvironment env =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        try {
            canvas.createBufferStrategy(2, config.getBufferCapabilities());
        } catch (AWTException e) {
            e.printStackTrace();
            System.exit(1);
        }

        bStrat = canvas.getBufferStrategy();

        // image's pixels will be manipulated and later image will be drawn
        // to the canvas
        image = config.createCompatibleImage(
                GameManager.getWidth(),
                GameManager.getHeight(),
                Transparency.TRANSLUCENT);
    }

    /**
     * Starts drawing to buffer.
     *
     * Gets graphics object and returns it.
     *
     * @return graphics object used to draw
     */
    public Graphics2D start() {

        g = (Graphics2D) bStrat.getDrawGraphics();

        // Flip graphics so that origo will be at the bottom-left corner
        g.translate(0, GameManager.getHeight());
        g.scale(GameManager.getWidth() / camera.transform.width,
                -(GameManager.getHeight() /camera.transform.height));

        return g;
    }

    /**
     * Stops drawing and shows next image.
     */
    public void stop() {

        // draw image to the buffer
        // g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(),null);
        // show buffered image
        bStrat.show();
    }

    /**
     * Sets current camera if given camera is not null.
     *
     * @param camera New camera
     */
    public void setCamera(Camera camera) {

        if (camera != null) {

            this.camera = camera;
        }
    }
}
