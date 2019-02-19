package by.guretsky.task01_objects.factory;

import by.guretsky.task01_objects.entity.Point;

import java.util.ArrayList;
import java.util.List;

public class PointFactoryImpl implements PointFactory {

    public Point createPoint(final double x, final double y) {
        return new Point(x, y);
    }

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
