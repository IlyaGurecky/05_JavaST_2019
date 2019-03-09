package by.guretsky.task2_threads.common_resource;

import by.guretsky.task2_threads.controller.TunnelController;
import by.guretsky.task2_threads.entity.Tunnel;
import by.guretsky.task2_threads.entity.Train;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Railway singleton class that consists of the two tunnels, tunnel controller,
 * two intersection queues.
 */
public final class RailwaySingleton {
    /**
     * Number of trains that can ride at the same time.
     */
    private static final int RAILWAY_CAPACITY = 20;
    /**
     * Singleton class object.
     */
    private static RailwaySingleton instance;
    /**
     * The {@link TunnelController} object that selects the tunnel for the train
     * and controls it in tunnel.
     */
    private TunnelController controller = new TunnelController();
    /**
     * {@link Tunnel} object.
     */
    private Tunnel firstTunnel = new Tunnel();
    /**
     * {@link Tunnel} object.
     */
    private Tunnel secondTunnel = new Tunnel();
    /**
     * Queue at the first intersection.
     */
    private Queue<Train> firstQueue = new ConcurrentLinkedQueue<>();
    /**
     * Queue at the second intersection.
     */
    private Queue<Train> secondQueue = new ConcurrentLinkedQueue<>();

    /**
     * Private constructor without parameters.
     */
    private RailwaySingleton() {
    }

    /**
     * The method get {@link RailwaySingleton} object.
     *
     * @return {@link RailwaySingleton} object
     */
    public static RailwaySingleton getInstance() {
        if (instance == null) {
            instance = new RailwaySingleton();
        }
        return instance;
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
     * {@link RailwaySingleton#firstTunnel} getter.
     *
     * @return {@link RailwaySingleton#firstTunnel}
     */
    public Tunnel getFirstTunnel() {
        return firstTunnel;
    }

    /**
     * {@link RailwaySingleton#secondTunnel} getter.
     *
     * @return {@link RailwaySingleton#secondTunnel}
     */
    public Tunnel getSecondTunnel() {
        return secondTunnel;
    }

    /**
     * The method puts the train in intersection queue.
     *
     * @param train {@link Train}
     */
    public void putInQueue(final Train train) {
        if (train.getDirection() == 1) {
            firstQueue.add(train);
        } else {
            secondQueue.add(train);
        }
    }

    /**
     * Takes {@link Train} object from the first intersection queue.
     *
     * @return {@link Train} object
     */
    public Train takeTrainFromFirstQueue() {
        return firstQueue.poll();
    }

    /**
     * Takes {@link Train} object from the second intersection queue.
     *
     * @return {@link Train} object
     */
    public Train takeTrainFromSecondQueue() {
        return secondQueue.poll();
    }

    /**
     * The method signals tunnel controller that train is waiting.
     *
     * @param train waiting train
     */
    public void signalToTunnelController(final Train train) {
        controller.chooseTunnel(train);
    }
}
