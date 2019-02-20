package by.guretsky.task01_objects.action;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;

/**
 * Class for determine the type of {@link Quadrangle}.
 */
public class ShapeExplorer {
    /**
     * Object, which used to calculate side length
     * {@link Calculator#calculateSide(Point, Point)}.
     */
    private final Calculator calculator = new Calculator();
    /**
     * {@link Quadrangle} object.
     */
    private Quadrangle quadrangle;

    /**
     * Private constructor.
     *
     * @see ShapeExplorer#ShapeExplorer(Quadrangle)
     */
    private ShapeExplorer() {
    }

    /**
     * Constructor, which used to create object with parameters.
     *
     * @param initQuadrangle {@link Quadrangle} object
     */
    public ShapeExplorer(final Quadrangle initQuadrangle) {
        this.quadrangle = initQuadrangle;
    }

    /**
     * The Method checks whether the figure is square.
     *
     * @return true if the figure is square
     */
    public boolean isSquare() {
        final int point1 = 0;
        final int point2 = 1;
        final int point3 = 2;
        final int point4 = 3;

        Double diagonal1 = calculator.calculateSide(quadrangle.getPoint(point1),
                quadrangle.getPoint(point3));
        Double diagonal2 = calculator.calculateSide(quadrangle.getPoint(point2),
                quadrangle.getPoint(point4));
        return diagonal1.compareTo(diagonal2) == 0;
    }

    /**
     * The Method checks whether the figure is rhombus.
     *
     * @return true if the figure is rhombus
     */
    public boolean isRhombus() {
        final int point1 = 0;
        final int point2 = 1;
        final int point3 = 2;
        final int point4 = 3;

        if (!isSquare()) {
            Double sideA = calculator.calculateSide(quadrangle.getPoint(point1),
                    quadrangle.getPoint(point2));
            Double sideB = calculator.calculateSide(quadrangle.getPoint(point2),
                    quadrangle.getPoint(point3));
            Double sideC = calculator.calculateSide(quadrangle.getPoint(point3),
                    quadrangle.getPoint(point4));
            Double sideD = calculator.calculateSide(quadrangle.getPoint(point4),
                    quadrangle.getPoint(point1));

            return (sideA.compareTo(sideB) == 0)
                    && (sideA.compareTo(sideC) == 0)
                    && (sideA.compareTo(sideD) == 0);
        } else {
            return false;
        }
    }

    /**
     * The method calculates the angular coefficient of the line.
     *
     * @param firstPointIndex  first side point
     * @param secondPointIndex second side point
     * @return angular coefficient of the line
     */
    private Double calcAngularCoefficient(final int firstPointIndex,
                                          final int secondPointIndex) {
        return (quadrangle.getPoint(firstPointIndex).getX()
                - quadrangle.getPoint(secondPointIndex).getX())
                / (quadrangle.getPoint(firstPointIndex).getY()
                - quadrangle.getPoint(secondPointIndex).getY());
    }

    /**
     * The method checks whether the figure is trapezium.
     *
     * @return true if the figure is trapezium
     */
    public boolean isTrapezium() {
        final int point1 = 0;
        final int point2 = 1;
        final int point3 = 2;
        final int point4 = 3;

        Double angularCoefficient1 = calcAngularCoefficient(point1, point2);
        Double angularCoefficient2 = calcAngularCoefficient(point2, point3);
        Double angularCoefficient3 = calcAngularCoefficient(point4, point3);
        Double angularCoefficient4 = calcAngularCoefficient(point1, point4);

        return (angularCoefficient1.compareTo(angularCoefficient3) == 0
                && angularCoefficient2.compareTo(angularCoefficient4) != 0)
                || (angularCoefficient2.compareTo(angularCoefficient4) == 0
                && angularCoefficient1.compareTo(angularCoefficient3) != 0);
    }

    /**
     * The method checks if the points are on the same half-plane.
     *
     * @param diagonalPoint1 first point of diagonal line
     * @param diagonalPoint2 second point of diagonal line
     * @param checkPoint1    point, which need to check
     * @param checkPoint2    point, which need to check
     * @return true if the checkPoints are on the same half-plane
     */
    private boolean isPointsInOneHalfPlane(final Point diagonalPoint1,
                                           final Point diagonalPoint2,
                                           final Point checkPoint1,
                                           final Point checkPoint2) {
        double result1 = ((checkPoint1.getX() - diagonalPoint1.getX())
                / (diagonalPoint2.getX() - diagonalPoint1.getX()))
                - ((checkPoint1.getY() - diagonalPoint1.getY())
                / (diagonalPoint2.getY() - diagonalPoint1.getY()));

        double result2 = ((checkPoint2.getX() - diagonalPoint1.getX())
                / (diagonalPoint2.getX() - diagonalPoint1.getX()))
                - ((checkPoint2.getY() - diagonalPoint1.getY())
                / (diagonalPoint2.getY() - diagonalPoint1.getY()));

        return (result1 > 0 && result2 > 0) || (result1 < 0 && result2 < 0);
    }

    /**
     * The method checks whether the figure is convex.
     *
     * @return true if the figure is convex
     */
    public boolean isConvex() {
        final int firstPoint = 0;
        final int secondPoint = 1;
        final int thirdPoint = 2;
        final int fourthPoint = 3;

        Point point1 = quadrangle.getPoint(firstPoint);
        Point point2 = quadrangle.getPoint(secondPoint);
        Point point3 = quadrangle.getPoint(thirdPoint);
        Point point4 = quadrangle.getPoint(fourthPoint);

        boolean isInOneHalfPlane1 = isPointsInOneHalfPlane(point1, point3,
                point2, point4);
        boolean isInOneHalfPlane2 = isPointsInOneHalfPlane(point2, point4,
                point1, point3);

        return !isInOneHalfPlane1 && !isInOneHalfPlane2;
    }
}
