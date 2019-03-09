package by.guretsky.task2_threads.entity;

import by.guretsky.task2_threads.common_resource.RailwaySingleton;
import by.guretsky.task2_threads.exception.IncorrectArgumentException;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Train implements Callable<Integer> {
    private int direction;
    private int id;
    private static int idCounter = 0;
    private int delayTime;

    public Train(final int trainDirection) throws IncorrectArgumentException {
        if (trainDirection != 1 && trainDirection != 2) {
            throw new IncorrectArgumentException("Argument is incorrect");
        }
        id = idCounter++;
        direction = trainDirection;
        delayTime = new Random().nextInt(4000);
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public Integer call() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(delayTime);
        if (direction == 1) {
            System.out.println("Train number " + id + " going to the "
                    + RailwaySingleton.getInstance().getFirstRailwayName()
                    + " tunnel");
            TimeUnit.MILLISECONDS.sleep(300);
            RailwaySingleton.getInstance().getInFirstQueue(this);
            System.out.println("Train number " + id + " in the first queue");
        } else {
            System.out.println("Train number " + id + " going to the "
                    + RailwaySingleton.getInstance().getSecondRailwayName()
                    + " tunnel");
            TimeUnit.MILLISECONDS.sleep(200);
            RailwaySingleton.getInstance().getInSecondQueue(this);
            System.out.println("Train number " + id + " in the second queue");
        }
        String selectedTunnelName
                = RailwaySingleton.getInstance().chooseTunnel(this);


        return id;
    }
}
