package by.guretsky.task2_threads.entity;

import by.guretsky.task2_threads.common_resource.RailwaySingleton;
import by.guretsky.task2_threads.exception.IncorrectArgumentException;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Class of the trains with properties direction, id, delay time.
 */
public class Train implements Callable<Integer> {
    /**
     * Train direction(1 or 2).
     */
    private int direction;
    /**
     * Object id.
     */
    private int id;
    /**
     * This variable helps to calc object id.
     */
    private static int idCounter = 0;
    /**
     * Train start delay.
     */
    private int delayTime;

    /**
     * Constructor with special parameters.
     *
     * @param trainDirection train direction
     * @throws IncorrectArgumentException if direction is incorrect
     */
    public Train(final int trainDirection) throws IncorrectArgumentException {
        if (trainDirection != 1 && trainDirection != 2) {
            throw new IncorrectArgumentException("Argument is incorrect");
        }
        id = idCounter++;
        direction = trainDirection;
        final int delayTimeRange = 6000;
        delayTime = new Random().nextInt(delayTimeRange);
    }

    /**
     * Direction field getter.
     *
     * @return direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Id field getter.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}.
     *
     * @return train id
     * @throws InterruptedException if interrupted while sleeping
     */
    @Override
    public Integer call() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(delayTime);
        final long timeToIntersection = 300;
        TimeUnit.MILLISECONDS.sleep(timeToIntersection);
        RailwaySingleton.getInstance().signalToTunnelController(this);
        return id;
    }
}
