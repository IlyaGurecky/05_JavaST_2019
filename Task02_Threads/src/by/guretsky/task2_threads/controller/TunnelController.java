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
                if (!tunnel1.getSemaphore().hasQueuedThreads()) {
                    clearTunnelConfig(tunnel1);
                }
                if (!tunnel2.getSemaphore().hasQueuedThreads()) {
                    clearTunnelConfig(tunnel2);
                }
            }
        }
    }

    private boolean checkTunnel(final Tunnel tunnel, final Train train) throws
            InterruptedException {
        if (tunnel.getTrainsDirection() == 0) {
            if (train.getDirection() == tunnel.getPreviousTrainsDirection()) {
                TimeUnit.MILLISECONDS.sleep(500);
                return (tunnel.getTrainsDirection() == 0);
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

    /**
     * This method pass train into the first tunnel.
     *
     * @param train train, which you need to pass
     * @throws InterruptedException if interrupted while sleeping
     */
    private void goThroughFirstTunnel(final Train train) throws
            InterruptedException {
        tunnel1.incrementDirectionCounter();
        tunnel1.setTrainsDirection(train.getDirection());
        tunnel1.getSemaphore().acquire();
        System.out.println("Train " + train.getId()
                + " in the FIRST tunnel");
        TimeUnit.MILLISECONDS.sleep(Tunnel.getTimeInTunnel());
        leaveTunnel(tunnel1, train.getId());
    }

    /**
     * This method pass train into the first tunnel.
     *
     * @param train train, which you need to pass
     * @throws InterruptedException if interrupted while sleeping
     */
    private void goThroughSecondTunnel(final Train train) throws
            InterruptedException {
        tunnel2.incrementDirectionCounter();
        tunnel2.setTrainsDirection(train.getDirection());
        tunnel2.getSemaphore().acquire();
        System.out.println("Train " + train.getId()
                + " in the SECOND tunnel");
        TimeUnit.MILLISECONDS.sleep(Tunnel.getTimeInTunnel());
        leaveTunnel(tunnel2, train.getId());
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
        final long delay = 5;
        TimeUnit.MILLISECONDS.sleep(delay);
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
        tunnel.setPreviousTrainsDirection(tunnel.getTrainsDirection());
        tunnel.setTrainsDirection(0);
        while (tunnel.getSemaphore().hasQueuedThreads()) {
            TimeUnit.MILLISECONDS.sleep(delayTime);
        }
        tunnel.resetOneDirectionCounter();
    }
}
