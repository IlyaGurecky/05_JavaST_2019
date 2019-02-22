package by.guretsky.task01_objects_b.factory;

import by.guretsky.task01_objects_b.entity.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Point factory. Include create methods for the one {@link Point} object and
 * for list of the points.
 */
public class PointFactoryImpl implements PointFactory {

    /**
     * {@inheritDoc}
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return
     */
    @Override
    public Point createPoint(final double x, final double y) {
        return new Point(x, y);
    }

    /**
     * The method creates list of the points.
     *
     * @param points list of the double numbers
     * @return list of the points
     */
    public List<Point> createPointsList(final List<Double> points) {
        List<Point> pointsList = new ArrayList<>();
        Point point;
        for (int i = 0; i < points.size() - 1; i += 2) {
            point = createPoint(points.get(i), points.get(i + 1));
            pointsList.add(point);
        }
        return pointsList;
    }
}
