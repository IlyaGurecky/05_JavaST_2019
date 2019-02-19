package by.guretsky.task01_objects.action;

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

    public ShapeExplorer(final Quadrangle quadrangle) {
        this.quadrangle = quadrangle;
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

    private Double getAngularCoeff(final int firstPointIndex,
                                   final int secondPointIndex) {
        return (quadrangle.getPoint(firstPointIndex).getX()
                - quadrangle.getPoint(secondPointIndex).getX())
                / (quadrangle.getPoint(firstPointIndex).getY()
                - quadrangle.getPoint(secondPointIndex).getY());
    }

    public boolean isTrapezium() {
        Double angularCoefficient1 = getAngularCoeff(0, 1);
        Double angularCoefficient2 = getAngularCoeff(1, 2);
        Double angularCoefficient3 = getAngularCoeff(3, 2);
        Double angularCoefficient4 = getAngularCoeff(0, 3);

        return (angularCoefficient1.equals(angularCoefficient3)
                && !angularCoefficient2.equals(angularCoefficient4)
                || angularCoefficient2.equals(angularCoefficient4)
                && !angularCoefficient1.equals(angularCoefficient3));
    }

    public boolean isConvex() {

        return false;
    }
}
