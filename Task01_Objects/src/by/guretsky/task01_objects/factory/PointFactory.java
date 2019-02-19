package by.guretsky.task01_objects.factory;

import by.guretsky.task01_objects.entity.Point;

public interface PointFactory extends Factory {
    Point createPoint(double x, double y);
}
