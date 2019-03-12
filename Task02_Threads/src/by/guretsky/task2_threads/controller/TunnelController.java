package by.guretsky.task2_threads.controller;

import by.guretsky.task2_threads.entity.Train;
import by.guretsky.task2_threads.entity.Tunnel;

import java.util.concurrent.TimeUnit;

/**
 * Class controller, which controls traffic in the tunnels.
 */
public class TunnelController {
    /**
     * First tunnel.
     */
    private Tunnel tunnel1;
    /**
     * Second tunnel.
     */
    private Tunnel tunnel2;

    /**
     * Public constructor with parameters.
     *
     * @param first  first tunnel
     * @param second second tunnel
     */
    public TunnelController(final Tunnel first, final Tunnel second) {
        tunnel1 = first;
        tunnel2 = second;
    }

    public void chooseTunnel(final Train train) throws InterruptedException {
        System.out.println("Train number " + train.getId() + " with direction "
                + train.getDirection() + " choose tunnel...");
        boolean stopSearching = false;
        while (!stopSearching) {
            if (train.getDirection() == tunnel1.getTrainsDirection()
                    || tunnel1.getTrainsDirection() == 0) {
                stopSearching = tryTunnel(tunnel1, train, tunnel2);
            } else if (train.getDirection() == tunnel2.getTrainsDirection()
                    || tunnel2.getTrainsDirection() == 0) {
                stopSearching = tryTunnel(tunnel2, train, tunnel1);
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

    private boolean tryTunnel(final Tunnel priorityTunnel, final Train train,
                              final Tunnel backupTunnel) throws
            InterruptedException {
        if (priorityTunnel.getTrainsDirection() == 0) {
            if (train.getDirection() == priorityTunnel.getPreviousDirection()) {
                if (train.getDirection() != backupTunnel.getPreviousDirection()) {
                    clearTunnelConfig(backupTunnel);
                    goThroughTunnel(backupTunnel, train);
                    return true;
                } else {
                    clearTunnelConfig(priorityTunnel);
                    goThroughTunnel(priorityTunnel, train);
                    return true;
                }
            } else {
                goThroughTunnel(priorityTunnel, train);
                return true;
            }
        } else {
            if (priorityTunnel.getOneDirectionCounter() == Tunnel.getOneDirectionTrainsLimit()) {
                if (backupTunnel.getOneDirectionCounter() == Tunnel.getOneDirectionTrainsLimit()) {
                    clearTunnelConfig(priorityTunnel);
                    clearTunnelConfig(backupTunnel);
                    goThroughTunnel(priorityTunnel, train);
                    return true;
                } else {
                    clearTunnelConfig(backupTunnel);
                    goThroughTunnel(backupTunnel, train);
                    return true;
                }
            } else {
                goThroughTunnel(priorityTunnel, train);
                return true;
            }
        }
    }

    /**
     * This method pass train into the tunnel.
     *
     * @param tunnel tunnel in which you need to pass
     * @param train  train, which you need to pass
     * @throws InterruptedException if interrupted while sleeping
     */
    private void goThroughTunnel(final Tunnel tunnel, final Train train) throws
            InterruptedException {
        tunnel.incrementDirectionCounter();
        tunnel.setTrainsDirection(train.getDirection());
        tunnel.getSemaphore().acquire();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Train " + train.getId() + " in the "
                + tunnel.getName() + " tunnel");
        TimeUnit.MILLISECONDS.sleep(Tunnel.getTimeInTunnel());
        leaveTunnel(tunnel, train.getId());
    }

    /**
     * This method allows the train to leave the tunnel.
     *
     * @param tunnel tunnel, which you need to leave
     * @param id     train id
     * @throws InterruptedException if interrupted while sleeping.
     */
    private void leaveTunnel(final Tunnel tunnel, final int id) throws
            InterruptedException {
        System.out.println(id + " go away.");
        tunnel.getSemaphore().release();
    }

    /**
     * This method clear tunnel configuration.
     *
     * @param tunnel tunnel you want to clear
     * @throws InterruptedException if interrupted while sleeping
     */
    private void clearTunnelConfig(final Tunnel tunnel) throws
            InterruptedException {
        final long delayTime = 5;
        while (tunnel.getSemaphore().hasQueuedThreads()) {
            TimeUnit.MILLISECONDS.sleep(delayTime);
        }
        tunnel.setPreviousDirection(tunnel.getTrainsDirection());
        tunnel.setTrainsDirection(0);
        tunnel.resetOneDirectionCounter();
    }
}
