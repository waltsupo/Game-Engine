package gameengine.utils;

/**
 * Does something after given time has passed.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1217
 * @since 1.7
 */
public class Timer {

    /**
     * How long to wait until running Job.
     */
    private float delay;

    /**
     * Method to run after delay.
     */
    private Job job;

    /**
     * Sets values for Timer.
     *
     * @param delay How long until given Job should be run
     * @param job Method to run after delay
     */
    public Timer(float delay, Job job) {

        this.delay = delay;
        this.job = job;
    }
}
