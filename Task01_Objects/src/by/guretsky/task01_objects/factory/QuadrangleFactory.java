package by.guretsky.task01_objects.factory;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import by.guretsky.task01_objects.exception.IncorrectQuadranglePointsException;

import java.util.List;

public interface QuadrangleFactory extends Factory {
    Quadrangle createQuadrangle(List<Point> points) throws
            IncorrectQuadranglePointsException;
}
