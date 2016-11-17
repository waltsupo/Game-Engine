package gameengine.core;

import gameengine.core.input.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Window for the game
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1117
 * @since 1.7
 */
public class Window {

    /**
     * JFrame for the game
     */
    private JFrame frame;

    /**
     * Canvas for drawing
     */
    private Canvas canvas;

    /**
     * Image for drawing
     */
    private BufferedImage image;

    /**
     * graphics for drawing
     */
    private Graphics2D g;

    /**
     * BufferStrategy for drawing
     */
    private BufferStrategy bStrat;

    /**
     * Creates window
     */
    public Window() {

        frame = new JFrame();

        // Prevent resizing
        frame.setResizable(false);

        // Close program on "close"
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        try {
            canvas.createBufferStrategy(2, config.getBufferCapabilities());
        } catch (AWTException e) {
            e.printStackTrace(); // TODO exception
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
     * Starts drawing to buffer
     *
     * Gets graphics object and returns it
     *
     * @return graphics object used to draw
     */
    public Graphics2D start() {

        g = (Graphics2D) bStrat.getDrawGraphics();

        // Flip graphics so that origo will be at the bottom-left corner
        g.translate(0, GameManager.getHeight());
        g.scale(1, -1);

        return g;
    }

    /**
     * Stops drawing and shows next image
     */
    public void stop() {

        // draw image to the buffer
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(),null);
        // show buffered image
        bStrat.show();
    }
}
