package by.guretsky.task01_objects.creator;

import by.guretsky.task01_objects.entity.Point;

import java.util.ArrayList;
import java.util.List;

public class PointsListCreator {
    public List<Point> createPoints(final List<Double> points) {
        List<Point> pointsList = new ArrayList<>();
        Point point;
        PointCreator creator = new PointCreator();
        for (int i = 0; i < points.size() - 1; i += 2) {
            point = creator.createPoint(points.get(i), points.get(i + 1));
            pointsList.add(point);
        }
        return pointsList;
    }
}
