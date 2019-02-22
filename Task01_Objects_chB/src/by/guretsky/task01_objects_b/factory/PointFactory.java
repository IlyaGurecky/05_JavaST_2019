package by.guretsky.task01_objects_b.factory;

import by.guretsky.task01_objects_b.entity.Point;

/**
 * Interface for point factory.
 */
public interface PointFactory extends Factory<Point> {
    /**
     * The method creates {@link Point} object.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return Point object with X, Y coordinates
     */
    Point createPoint(double x, double y);
}
