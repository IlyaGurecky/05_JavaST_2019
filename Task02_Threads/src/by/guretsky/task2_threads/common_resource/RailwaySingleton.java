package by.guretsky.task2_threads.common_resource;

import by.guretsky.task2_threads.controller.TunnelChooser;
import by.guretsky.task2_threads.entity.Tunnel;
import by.guretsky.task2_threads.entity.Train;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public final class RailwaySingleton {
    private static final int TIME_IN_TUNNEL = 1000;
    private static final RailwaySingleton INSTANCE
            = new RailwaySingleton();
    private final TunnelChooser chooser = new TunnelChooser();
    private final Tunnel firstTunnel = new Tunnel("First");
    private final Tunnel secondTunnel = new Tunnel("Second");
    private Queue<Train> firstQueue = new ConcurrentLinkedQueue<>();
    private Queue<Train> secondQueue = new ConcurrentLinkedQueue<>();

    private RailwaySingleton() {
    }

    public static RailwaySingleton getInstance() {
        return INSTANCE;
    }
    public String getFirstRailwayName() {
        return firstTunnel.getName();
    }

    public String getSecondRailwayName() {
        return secondTunnel.getName();
    }

    public void getInFirstQueue(Train train) {
        firstQueue.add(train);
    }

    public void getInSecondQueue(Train train) {
        secondQueue.add(train);
    }

    public void getInFirstTunnel(Train train) throws InterruptedException {
        firstTunnel.setEmpty(false);
        firstTunnel.getInQueue(train);
        TimeUnit.MILLISECONDS.sleep(TIME_IN_TUNNEL);
    }

    public void getInSecondTunnel(Train train) throws InterruptedException {
        secondTunnel.setEmpty(false);
        secondTunnel.getInQueue(train);
        TimeUnit.MILLISECONDS.sleep(TIME_IN_TUNNEL);
    }

    public void freeFirstTunnel() {
        firstTunnel.setEmpty(true);
    }

    public void freeSecondTunnel() {
        secondTunnel.setEmpty(true);
    }

    public Train takeTrainFromFirstQueue() {
        return firstQueue.poll();
    }

    public Train peekTrainFromFirstQueue() {
        return firstQueue.peek();
    }

    public Train takeTrainFromSecondQueue() {
        return secondQueue.poll();
    }

    public Train peekTrainFromSecondQueue() {
        return secondQueue.peek();
    }

    public void leaveFirstTunnel() {
        firstTunnel.leaveQueue();
    }

    public void leaveSecondTunnel() {
        secondTunnel.leaveQueue();
    }

    public boolean firstQueueIsEmpty() {
        return firstQueue.isEmpty();
    }

    public boolean secondQueueIsEmpty() {
        return secondQueue.isEmpty();
    }

    public String chooseTunnel(Train train) throws InterruptedException {
        return chooser.chooseTunnel(train);
    }
}
