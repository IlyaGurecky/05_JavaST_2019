package by.guretsky.task01_objects.creator;

import by.guretsky.task01_objects.entity.PointForQuadrangle;

public class PointCreator {
    public PointForQuadrangle createPoint(final Double x, final Double y) {
        return new PointForQuadrangle(x, y);
    }
}
