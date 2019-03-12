                                Variant 8

File info: trains directions(1 or 2 only).

About program:
    In this program ExecutorService invoke trains(Executor pool == railway system capacity).
    Trains start to move with a delay, which calculated randomly in the object constructor(Schedule simulation).
    After this delay the train starts moving. When the train approaches the tunnels it signals to the TunnelController
    that it is waiting for distribution.

    After this TunnelController checks the tunnels. If tunnel is empty or trains direction in tunnel is equals to
    train direction and tunnel one direction counter != one direction limit, train go through this tunnel.
    If tunnel one direction counter == one direction limit, train go through another tunnel, but at first waiting for its release by other trains.
    If one direction counter of the both tunnels == one direction limit, tunnels configurations cleared and train goes
    through the one of this tunnel.

    In the tunnel can go a limited number of trains at the same time(Used Semaphore).

    In the console may be inconsistencies because of System.out.println() delay.