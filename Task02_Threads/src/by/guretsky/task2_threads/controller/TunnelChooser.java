package by.guretsky.task2_threads.controller;

import by.guretsky.task2_threads.common_resource.RailwaySingleton;
import by.guretsky.task2_threads.entity.Train;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TunnelChooser {
    private static final int TRAINS_IN_TUNNEL_LIMIT = 3;
    private static final int ONE_DIRECTION_TRAINS_LIMIT = 10;
    private final Semaphore firstTunnelSemaphore =
            new Semaphore(TRAINS_IN_TUNNEL_LIMIT, true);
    private final Semaphore secondTunnelSemaphore =
            new Semaphore(TRAINS_IN_TUNNEL_LIMIT, true);
    private int firstOneDirectionCounter = 0;
    private int secondOneDirectionCounter = 0;
    private RailwaySingleton railway = RailwaySingleton.getInstance();

    public String chooseTunnel(Train train) throws InterruptedException {
        if (train.getDirection() == 1) {
            while (true) {
                if (train != railway.peekTrainFromFirstQueue()) {
                    break;
                }
            }
            if (firstOneDirectionCounter < ONE_DIRECTION_TRAINS_LIMIT) {
                firstTunnelSemaphore.acquire();
                firstOneDirectionCounter++;
                railway.getInFirstTunnel(railway.takeTrainFromFirstQueue());
                railway.leaveFirstTunnel();
                firstTunnelSemaphore.release();
            } else if (firstOneDirectionCounter == ONE_DIRECTION_TRAINS_LIMIT) {

            }

        }
        return "";
    }
}
