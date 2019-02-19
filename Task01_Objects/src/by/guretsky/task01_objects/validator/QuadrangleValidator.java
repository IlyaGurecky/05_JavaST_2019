package by.guretsky.task01_objects.validator;

import by.guretsky.task01_objects.entity.Point;

import java.util.List;

public class QuadrangleValidator {

    public boolean isQuadrangle(final List<Point> points) {
        final int firstPoint = 0;
        final int secondPoint = 1;
        final int thirdPoint = 2;
        final int fourthPoint = 3;

        Point point1 = points.get(firstPoint);
        Point point2 = points.get(secondPoint);
        Point point3 = points.get(thirdPoint);
        Point point4 = points.get(fourthPoint);

        boolean firstCheck = checkPoints(point1, point2, point3);
        boolean secondCheck = checkPoints(point1, point2, point4);
        boolean thirdCheck = checkPoints(point2, point3, point4);
        boolean fourthCheck = checkPoints(point1, point3, point4);

        return !firstCheck && !secondCheck && !thirdCheck && !fourthCheck;
    }

    private boolean checkPoints(final Point point1, final Point point2,
                                final Point point3) {
        Double resultOnXPoint = (point1.getX() - point2.getX())
                / (point3.getX() - point2.getX());

        Double resultOnYPoint = (point1.getY() - point2.getY())
                / (point3.getY() - point2.getY());

        return resultOnXPoint.equals(resultOnYPoint);
    }
}
