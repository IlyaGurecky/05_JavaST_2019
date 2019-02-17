package by.guretsky.task01_objects.creator;

import by.guretsky.task01_objects.entity.Point;

public class PointCreator {
    public Point createPoint(final Double x, final Double y) {
        return new Point(x, y);
    }
}
