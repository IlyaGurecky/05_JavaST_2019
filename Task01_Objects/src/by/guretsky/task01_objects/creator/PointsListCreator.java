package by.guretsky.task01_objects.creator;

import by.guretsky.task01_objects.entity.PointForQuadrangle;

import java.util.ArrayList;
import java.util.List;

public class PointsListCreator {
    public List<PointForQuadrangle> createPoints(List<Double> points) {
        List<PointForQuadrangle> pointsList = new ArrayList<>();
        PointForQuadrangle point;
        PointCreator creator = new PointCreator();
        for (int i = 0; i < points.size() - 1; i += 2) {
          point = creator.createPoint(points.get(i), points.get(i + 1));
          pointsList.add(point);
        }
        return pointsList;
    }
}
