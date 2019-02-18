package by.guretsky.task01_objects.action;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class QuadrangleType {
    private static final Logger LOGGER = LogManager
            .getLogger(QuadrangleType.class);
    private final Calculator calculator = new Calculator();
    private List<Point> points;


    private QuadrangleType() {
    }

    public QuadrangleType(final Quadrangle quadrangle) {
        points = quadrangle.getPoints();
    }

    public boolean isSquare() {
        Double diagonal1 = calculator.calculateSide(points.get(0),
                points.get(2));
        Double diagonal2 = calculator.calculateSide(points.get(1),
                points.get(3));
        if (diagonal1.equals(diagonal2)) {
            LOGGER.info("This quadrangle is square");
            return true;
        } else {
            return false;
        }
    }

    public boolean isRhombus() {
        if (!isSquare()) {
            Double sideA = calculator.calculateSide(points.get(0),
                    points.get(1));
            Double sideB = calculator.calculateSide(points.get(1),
                    points.get(2));
            Double sideC = calculator.calculateSide(points.get(2),
                    points.get(3));
            Double sideD = calculator.calculateSide(points.get(3),
                    points.get(0));
            return sideA.equals(sideB) && sideA.equals(sideC)
                    && sideA.equals(sideD);
        } else {
            return false;
        }
    }

    public boolean isTrapezium() {
        return false;
    }

    public boolean isConvex() {
        return false;
    }
}
