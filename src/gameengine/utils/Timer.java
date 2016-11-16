package gameengine.utils;

/**
 * Timer to see how much time something takes.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.fi
 * @version 2016.1002
 * @since 1.7
 */
public class Timer {

    /**
     * Tells the time when timer was started.
     */
    private double startTime;

    /**
     * Tells how much time passed between start and stop.
     */
    public double delta;

    /**
     * Defines values.
     */
    public Timer() {

        startTime = 0;
        delta = 0;
    }

    /**
     * Sets start time to current time.
     */
    public void start() {

        startTime = System.nanoTime();
    }

    /**
     * Calculates time between start and stop.
     */
    public void stop() {

        delta = (System.nanoTime() - startTime) / 1000000000;
    }
}
