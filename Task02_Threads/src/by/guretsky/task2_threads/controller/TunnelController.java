package by.guretsky.task2_threads.controller;

import by.guretsky.task2_threads.common_resource.RailwaySingleton;
import by.guretsky.task2_threads.entity.Train;
import by.guretsky.task2_threads.entity.Tunnel;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TunnelController {
    private Tunnel tunnel1;
    private Tunnel tunnel2;

    public TunnelController(final Tunnel first, final Tunnel second) {
        tunnel1 = first;
        tunnel2 = second;
    }

    public void chooseTunnel(Train train) throws InterruptedException {
        while (true) {
            if (train.getDirection() == tunnel1.getTrainsDirection()
                    || tunnel1.getTrainsDirection() == 0) {
                if (checkTunnel(tunnel1, train)) {
                    goThroughFirstTunnel(train);
                    break;
                }
            } else if (train.getDirection() == tunnel2.getTrainsDirection()
                    || tunnel2.getTrainsDirection() == 0) {
                if (checkTunnel(tunnel2, train)) {
                    goThroughSecondTunnel(train);
                    break;
                }
            } else {
                TimeUnit.MILLISECONDS.sleep(100);
                if (!tunnel1.getSemaphore().hasQueuedThreads()) {
                    clearTunnelConfig(tunnel1);
                }
                if (!tunnel2.getSemaphore().hasQueuedThreads()) {
                    clearTunnelConfig(tunnel2);
                }
            }
        }
    }

    private void goThroughFirstTunnel(final Train train) throws
            InterruptedException {
        tunnel1.incrementDirectionCounter();
        tunnel1.setTrainsDirection(train.getDirection());
        tunnel1.getSemaphore().acquire();
        System.out.println("Train number " + train.getId()
                + " in the FIRST tunnel");
        TimeUnit.MILLISECONDS.sleep(Tunnel.getTimeInTunnel());
        leaveTunnel(tunnel1);
    }

    private void goThroughSecondTunnel(final Train train) throws
            InterruptedException {
        tunnel2.incrementDirectionCounter();
        tunnel2.setTrainsDirection(train.getDirection());
        tunnel2.getSemaphore().acquire();
        System.out.println("Train number " + train.getId()
                + " in the SECOND tunnel");
        TimeUnit.MILLISECONDS.sleep(Tunnel.getTimeInTunnel());
        leaveTunnel(tunnel2);
    }

    private void leaveTunnel(final Tunnel tunnel) throws InterruptedException {
        final long delay = 10;
        TimeUnit.MILLISECONDS.sleep(delay);
        tunnel.getSemaphore().release();
    }

    private void clearTunnelConfig(final Tunnel tunnel) throws
            InterruptedException {
        tunnel.setPreviousTrainsDirection(tunnel.getTrainsDirection());
        tunnel.setTrainsDirection(0);

        while (tunnel.getSemaphore().hasQueuedThreads()) {
            TimeUnit.MILLISECONDS.sleep(100);
        }
        tunnel.resetOneDirectionCounter();
    }

    private boolean checkTunnel(final Tunnel tunnel, final Train train) throws
            InterruptedException {
        if (tunnel.getTrainsDirection() == 0) {
            if (train.getDirection() == tunnel.getPreviousTrainsDirection()) {
                TimeUnit.MILLISECONDS.sleep(500);
                if (tunnel.getTrainsDirection() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            if (tunnel.getOneDirectionCounter()
                    == Tunnel.getOneDirectionTrainsLimit()) {
                clearTunnelConfig(tunnel);
                return false;
            } else {
                return true;
            }
        }

    }
}
