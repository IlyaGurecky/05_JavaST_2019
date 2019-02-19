package by.guretsky.task01_objects.action;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShapeExplorer {
    private static final Logger LOGGER = LogManager
            .getLogger(ShapeExplorer.class);
    private final Calculator calculator = new Calculator();
    private Quadrangle quadrangle;

    private ShapeExplorer() {
    }

    public ShapeExplorer(final Quadrangle initQuadrangle) {
        this.quadrangle = initQuadrangle;
    }

    public boolean isSquare() {
        Double diagonal1 = calculator.calculateSide(quadrangle.getPoint(0),
                quadrangle.getPoint(2));
        Double diagonal2 = calculator.calculateSide(quadrangle.getPoint(1),
                quadrangle.getPoint(3));
        if (diagonal1.equals(diagonal2)) {
            LOGGER.info("This quadrangle is square");
            return true;
        } else {
            return false;
        }
    }

    public boolean isRhombus() {
        if (!isSquare()) {
            Double sideA = calculator.calculateSide(quadrangle.getPoint(0),
                    quadrangle.getPoint(1));
            Double sideB = calculator.calculateSide(quadrangle.getPoint(1),
                    quadrangle.getPoint(2));
            Double sideC = calculator.calculateSide(quadrangle.getPoint(2),
                    quadrangle.getPoint(3));
            Double sideD = calculator.calculateSide(quadrangle.getPoint(3),
                    quadrangle.getPoint(0));

            return sideA.equals(sideB) && sideA.equals(sideC)
                    && sideA.equals(sideD);
        } else {
            return false;
        }
    }

    private Double getAngularCoefficient(final int firstPointIndex,
                                         final int secondPointIndex) {
        return (quadrangle.getPoint(firstPointIndex).getX()
                - quadrangle.getPoint(secondPointIndex).getX())
                / (quadrangle.getPoint(firstPointIndex).getY()
                - quadrangle.getPoint(secondPointIndex).getY());
    }

    public boolean isTrapezium() {
        Double angularCoefficient1 = getAngularCoefficient(0, 1);
        Double angularCoefficient2 = getAngularCoefficient(1, 2);
        Double angularCoefficient3 = getAngularCoefficient(3, 2);
        Double angularCoefficient4 = getAngularCoefficient(0, 3);

        return (angularCoefficient1.equals(angularCoefficient3)
                && !angularCoefficient2.equals(angularCoefficient4)
                || angularCoefficient2.equals(angularCoefficient4)
                && !angularCoefficient1.equals(angularCoefficient3));
    }

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

    public boolean isConvex() {
        Point point1 = quadrangle.getPoint(0);
        Point point2 = quadrangle.getPoint(1);
        Point point3 = quadrangle.getPoint(2);
        Point point4 = quadrangle.getPoint(3);

        boolean isInOneHalfPlane1 = isPointsInOneHalfPlane(point1, point3,
                point2, point4);
        boolean isInOneHalfPlane2 = isPointsInOneHalfPlane(point2, point4,
                point1, point3);

        return !isInOneHalfPlane1 && !isInOneHalfPlane2;
    }
}
