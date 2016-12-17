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
     * How much time has passed since start.
     */
    private float time;

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

    /**
     * Adds passed time to Timer.
     *
     * Adds passed time to Timer and if enough time has passed,
     * runs given method.
     *
     * @param delta Time passed.
     */
    public void addTime(float delta) {

        time += delta;

        if (time >= delay) {
            job.job();
            time -= delay;
        }
    }
}
