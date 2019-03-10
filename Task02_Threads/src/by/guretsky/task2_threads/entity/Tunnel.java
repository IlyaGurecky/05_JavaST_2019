package by.guretsky.task2_threads.entity;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The class is used to store information about tunnel.
 */
public class Tunnel {
    /**
     * Train time in tunnel.
     */
    private static final int TIME_IN_TUNNEL = 1000;
    /**
     * Amount of one direction trains that can pass through the tunnel.
     */
    private static final int ONE_DIRECTION_TRAINS_LIMIT = 5;
    /**
     * Tunnel capacity.
     */
    private static final int TRAINS_IN_TUNNEL_LIMIT = 3;
    /**
     * Tunnel semaphore.
     */
    private final Semaphore semaphore =
            new Semaphore(TRAINS_IN_TUNNEL_LIMIT, true);
    /**
     * Direction of the passed trains.
     * (1-first direction, 2-second direction, 0-free).
     */
    private int trainsDirection;

    /**
     * Previous direction.
     * (1-first direction, 2-second direction, 0-free).
     */
    private int previousTrainsDirection;

    /**
     * Counts number of one direction trains, which passed through the tunnel.
     */
    private AtomicInteger oneDirectionCounter = new AtomicInteger(0);

    /**
     * {@link Tunnel#TIME_IN_TUNNEL} getter.
     *
     * @return {@link Tunnel#TIME_IN_TUNNEL}
     */
    public static int getTimeInTunnel() {
        return TIME_IN_TUNNEL;
    }

    /**
     * {@link Tunnel#ONE_DIRECTION_TRAINS_LIMIT} getter.
     *
     * @return {@link Tunnel#ONE_DIRECTION_TRAINS_LIMIT}
     */
    public static int getOneDirectionTrainsLimit() {
        return ONE_DIRECTION_TRAINS_LIMIT;
    }

    /**
     * {@link Tunnel#previousTrainsDirection} getter.
     *
     * @return {@link Tunnel#previousTrainsDirection}
     */
    public int getPreviousTrainsDirection() {
        return previousTrainsDirection;
    }

    /**
     * {@link Tunnel#previousTrainsDirection} setter.
     *
     * @param prevDirection previous direction
     */
    public void setPreviousTrainsDirection(final int prevDirection) {
        this.previousTrainsDirection = prevDirection;
    }

    /**
     * Semaphore getter.
     *
     * @return {@link Tunnel#semaphore}
     */
    public Semaphore getSemaphore() {
        return semaphore;
    }

    /**
     * {@link Tunnel#trainsDirection} getter.
     *
     * @return {@link Tunnel#trainsDirection}
     */
    public int getTrainsDirection() {
        return trainsDirection;
    }

    /**
     * {@link Tunnel#oneDirectionCounter} getter.
     *
     * @return {@link Tunnel#oneDirectionCounter}
     */
    public int getOneDirectionCounter() {
        return oneDirectionCounter.get();
    }

    /**
     * {@link Tunnel#trainsDirection} setter.
     *
     * @param direction train direction
     */
    public void setTrainsDirection(final int direction) {
        this.trainsDirection = direction;
    }

    /**
     * The method increments {@link Tunnel#oneDirectionCounter}.
     */
    public void incrementDirectionCounter() {
        oneDirectionCounter.incrementAndGet();
    }

    /**
     * This method reset {@link Tunnel#oneDirectionCounter} to 0.
     */
    public void resetOneDirectionCounter() {
        oneDirectionCounter.set(0);
    }
}
