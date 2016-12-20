package gameengine.utils;

import java.util.LinkedList;

/**
 * Handles Timers.
 *
 * Adds delta-time to Timers every update, and removes Timers that are done.
 *
 * @author Valtteri Poutanen valtteri.poutanen@hotmail.com
 * @version 2016.1217
 * @since 1.7
 */
public class Timers {

    /**
     * Contains all Timers used in game.
     */
    private static LinkedList<Timer> timers;

    /**
     * Defines timers-list.
     *
     * DO NOT CALL UNLESS YOU KNOW WHAT YOU ARE DOING
     */
    public static void setup() {

        timers = new LinkedList<>();
    }

    /**
     * Updates timers.
     *
     * @param delta Time elapsed since last update
     */
    public static void updateTimers(float delta) {

        LinkedList<Timer> toRemove = new LinkedList<>();

        for (Timer timer : timers) {
            if (timer.addTime(delta)) {
                toRemove.add(timer);
            }
        }

        for (Timer timer : toRemove) {
            timers.remove(timer);
        }
    }

    /**
     * Adds timer to be updated.
     *
     * @param timer Timer to update
     */
    public static void addTimer(Timer timer) {

        timers.add(timer);
    }
}
