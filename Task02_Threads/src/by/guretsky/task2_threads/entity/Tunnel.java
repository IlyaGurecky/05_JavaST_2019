package by.guretsky.task2_threads.entity;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Tunnel {
    private boolean isEmpty;
    private String name;
    private Queue<Train> trainQueue;

    public Tunnel(final String railwayName) {
        name = railwayName;
        trainQueue = new ConcurrentLinkedQueue<>();
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getName() {
        return name;
    }

    public void getInQueue(Train train) {
        trainQueue.add(train);
    }

    public void leaveQueue() {
        trainQueue.poll();
    }
}
