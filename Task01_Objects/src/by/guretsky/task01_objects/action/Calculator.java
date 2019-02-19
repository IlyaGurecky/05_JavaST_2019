package by.guretsky.task01_objects.action;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;

public class Calculator {
    public Double square(final Quadrangle quadrangle) {
        final int firstPoint = 0;
        final int secondPoint = 1;
        final int thirdPoint = 2;
        final int fourthPoint = 3;

        return Math.abs(quadrangle.getPoint(firstPoint).getX()
                * quadrangle.getPoint(secondPoint).getY()
                + quadrangle.getPoint(secondPoint).getX()
                * quadrangle.getPoint(thirdPoint).getY()
                + quadrangle.getPoint(thirdPoint).getX()
                * quadrangle.getPoint(fourthPoint).getY()
                + quadrangle.getPoint(fourthPoint).getX()
                * quadrangle.getPoint(firstPoint).getY()
                - quadrangle.getPoint(secondPoint).getX()
                * quadrangle.getPoint(firstPoint).getY()
                - quadrangle.getPoint(thirdPoint).getX()
                * quadrangle.getPoint(secondPoint).getY()
                - quadrangle.getPoint(fourthPoint).getX()
                * quadrangle.getPoint(thirdPoint).getY()
                - quadrangle.getPoint(firstPoint).getX()
                * quadrangle.getPoint(fourthPoint).getY()) / 2;
    }

    public Double calculateSide(final Point point1,
                                final Point point2) {
        return Math.sqrt((point1.getX() - point2.getX())
                * (point1.getX() - point2.getX()) + (point1.getY()
                - point2.getY()) * (point1.getY() - point2.getY()));
    }

    public Double perimeter(final Quadrangle quadrangle) {
        final int firstPoint = 0;
        final int secondPoint = 1;
        final int thirdPoint = 2;
        final int fourthPoint = 3;

        Double firstSide = calculateSide(quadrangle.getPoint(firstPoint),
                quadrangle.getPoint(secondPoint));
        Double secondSide = calculateSide(quadrangle.getPoint(secondPoint),
                quadrangle.getPoint(thirdPoint));
        Double thirdSide = calculateSide(quadrangle.getPoint(thirdPoint),
                quadrangle.getPoint(fourthPoint));
        Double fourthSide = calculateSide(quadrangle.getPoint(fourthPoint),
                quadrangle.getPoint(firstPoint));

        return firstSide + secondSide + thirdSide + fourthSide;
    }
}
