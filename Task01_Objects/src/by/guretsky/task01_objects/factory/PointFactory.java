package by.guretsky.task01_objects.factory;

import by.guretsky.task01_objects.entity.Point;

/**
 * Interface for point factory.
 */
public interface PointFactory extends Factory {
    /**
     * Method create {@link Point} object.
     * @param x X coordinate
     * @param y Y coordinate
     * @return Point object with X, Y coordinates
     */
    Point createPoint(double x, double y);
}
