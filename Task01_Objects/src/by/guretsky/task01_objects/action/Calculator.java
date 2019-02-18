package by.guretsky.task01_objects.action;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;

import java.util.List;

public class Calculator {
    private List<Point> points;

    public Double square(final Quadrangle quadrangle) {
        points = quadrangle.getPoints();

        return Math.abs(points.get(0).getX() * points.get(1).getY()
                + points.get(1).getX() * points.get(2).getY()
                + points.get(2).getX() * points.get(3).getY()
                + points.get(3).getX() * points.get(0).getY()
                - points.get(1).getX() * points.get(0).getY()
                - points.get(2).getX() * points.get(1).getY()
                - points.get(3).getX() * points.get(2).getY()
                - points.get(0).getX() * points.get(3).getY()) / 2;
    }

    public Double calculateSide(final Point point1,
                                final Point point2) {
        return Math.sqrt((point1.getX() - point2.getX())
                * (point1.getX() - point2.getX()) + (point1.getY()
                - point2.getY()) * (point1.getY() - point2.getY()));
    }

    public Double perimeter(final Quadrangle quadrangle) {
        points = quadrangle.getPoints();

        Double firstSide = calculateSide(points.get(0), points.get(1));
        Double secondSide = calculateSide(points.get(1), points.get(2));
        Double thirdSide = calculateSide(points.get(2), points.get(3));
        Double fourthSide = calculateSide(points.get(3), points.get(0));

        return firstSide + secondSide + thirdSide + fourthSide;
    }
}
