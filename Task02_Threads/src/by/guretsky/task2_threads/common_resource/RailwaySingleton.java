package by.guretsky.task2_threads.common_resource;

import by.guretsky.task2_threads.controller.TunnelController;
import by.guretsky.task2_threads.entity.Tunnel;
import by.guretsky.task2_threads.entity.Train;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Railway singleton class that consists of the two tunnels, tunnel controller,
 * two intersection queues.
 */
public final class RailwaySingleton {
    /**
     * Logger, which used to log events.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RailwaySingleton.class);
    /**
     * Number of trains that can ride at the same time.
     */
    private static final int RAILWAY_CAPACITY = 25;
    /**
     * {@link Tunnel} object.
     */
    private Tunnel firstTunnel = new Tunnel("FIRST");
    /**
     * {@link Tunnel} object.
     */
    private Tunnel secondTunnel = new Tunnel("SECOND");

    /**
     * The {@link TunnelController} object that selects the tunnel for the train
     * and controls it in tunnel.
     */
    private TunnelController controller
            = new TunnelController(firstTunnel, secondTunnel);

    /**
     * Private constructor without parameters.
     */
    private RailwaySingleton() {
    }

    /**
     * Inner static class for thread-safe singleton.
     */
    private static class InstanceHolder {
        /**
         * Singleton class object.
         */
        private static final RailwaySingleton INSTANCE = new RailwaySingleton();
    }

    /**
     * The method get {@link RailwaySingleton} object.
     *
     * @return {@link RailwaySingleton} object
     */
    public static RailwaySingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * {@link RailwaySingleton#RAILWAY_CAPACITY} getter.
     *
     * @return {@link RailwaySingleton#RAILWAY_CAPACITY}
     */
    public static int getRailwayCapacity() {
        return RAILWAY_CAPACITY;
    }

    /**
     * The method signals tunnel controller that train is waiting.
     *
     * @param train waiting train
     */
    public void signalToTunnelController(final Train train) {
        try {
            controller.chooseTunnel(train);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted Exception", e);
        }
    }
}
