package by.guretsky.task01_objects.action;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;

public class Calculator {
    public Double square(final Quadrangle quadrangle) {
        return Math.abs(quadrangle.getPoint(0).getX()
                * quadrangle.getPoint(1).getY()
                + quadrangle.getPoint(1).getX()
                * quadrangle.getPoint(2).getY()
                + quadrangle.getPoint(2).getX()
                * quadrangle.getPoint(3).getY()
                + quadrangle.getPoint(3).getX()
                * quadrangle.getPoint(0).getY()
                - quadrangle.getPoint(1).getX()
                * quadrangle.getPoint(0).getY()
                - quadrangle.getPoint(2).getX()
                * quadrangle.getPoint(1).getY()
                - quadrangle.getPoint(3).getX()
                * quadrangle.getPoint(2).getY()
                - quadrangle.getPoint(0).getX()
                * quadrangle.getPoint(3).getY()) / 2;
    }

    public Double calculateSide(final Point point1,
                                final Point point2) {
        return Math.sqrt((point1.getX() - point2.getX())
                * (point1.getX() - point2.getX()) + (point1.getY()
                - point2.getY()) * (point1.getY() - point2.getY()));
    }

    public Double perimeter(final Quadrangle quadrangle) {
        Double firstSide = calculateSide(quadrangle.getPoint(0),
                quadrangle.getPoint(1));
        Double secondSide = calculateSide(quadrangle.getPoint(1),
                quadrangle.getPoint(2));
        Double thirdSide = calculateSide(quadrangle.getPoint(2),
                quadrangle.getPoint(3));
        Double fourthSide = calculateSide(quadrangle.getPoint(3),
                quadrangle.getPoint(0));

        return firstSide + secondSide + thirdSide + fourthSide;
    }
}
