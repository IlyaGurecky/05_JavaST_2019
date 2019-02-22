package by.guretsky.task01_objects_b.action;

import by.guretsky.task01_objects_b.entity.Point;
import by.guretsky.task01_objects_b.entity.Quadrangle;

/**
 * Class include methods, which calculate square, perimeter and side length
 * of the  {@link Quadrangle}.
 */
public class Calculator {
    /**
     * The method calculates square of the {@link Quadrangle}.
     *
     * @param quadrangle Quadrangle object
     * @return square of the {@link Quadrangle}
     */
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

    /**
     * The method calculates {@link Quadrangle} side length.
     *
     * @param point1 first side point
     * @param point2 second side point
     * @return length of the side
     */
    public Double calculateSide(final Point point1,
                                final Point point2) {
        return Math.sqrt((point1.getX() - point2.getX())
                * (point1.getX() - point2.getX()) + (point1.getY()
                - point2.getY()) * (point1.getY() - point2.getY()));
    }

    /**
     * The method calculates perimeter of the {@link Quadrangle} object.
     *
     * @param quadrangle {@link Quadrangle} object
     * @return perimeter of the quadrangle
     */
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
