package by.guretsky.task2_threads.controller;

import by.guretsky.task2_threads.common_resource.RailwaySingleton;
import by.guretsky.task2_threads.entity.Train;
import by.guretsky.task2_threads.entity.Tunnel;

import java.util.concurrent.TimeUnit;

public class TunnelController {
    private Tunnel tunnel1 = RailwaySingleton.getInstance().getFirstTunnel();
    private Tunnel tunnel2 = RailwaySingleton.getInstance().getSecondTunnel();

    public void chooseTunnel(final Train train) {
        System.out.println("pass");
    }

    private void goThroughFirstTunnel(final Train train) throws
            InterruptedException {
        //заполнять поля тоннеля
        System.out.println("Train number " + train.getId()
                + " in the FIRST tunnel");
        TimeUnit.MILLISECONDS.sleep(Tunnel.getTimeInTunnel());
    }

    private void goThroughSecondTunnel(final Train train) throws
            InterruptedException {
        //заполнять поля тоннеля
        System.out.println("Train number " + train.getId()
                + " in the SECOND tunnel");
        TimeUnit.MILLISECONDS.sleep(Tunnel.getTimeInTunnel());
    }
}
