package by.guretsky.task01_objects.validator;


import by.guretsky.task01_objects.entity.PointForQuadrangle;

import java.util.List;

public class QuadrangleValidator {

    public boolean isQuadrangle(final List<PointForQuadrangle> points) {
        PointForQuadrangle point1 = points.get(0);
        PointForQuadrangle point2 = points.get(1);
        PointForQuadrangle point3 = points.get(2);
        PointForQuadrangle point4 = points.get(3);

        boolean firstCheck = checkPoints(point1, point2, point3);
        boolean secondCheck = checkPoints(point1, point2, point4);
        boolean thirdCheck = checkPoints(point2, point3, point4);
        boolean fourthCheck = checkPoints(point1, point3, point4);

        if (firstCheck || secondCheck || thirdCheck || fourthCheck) {
            return false;
        } else {
            return true;
        }

    }

    private boolean checkPoints(final PointForQuadrangle point1,
                                final PointForQuadrangle point2,
                                final PointForQuadrangle point3) {
        Double resultOnXPoint = (point1.getX() - point2.getX())
                / (point3.getX() - point2.getX());

        Double resultOnYPoint = (point1.getY() - point2.getY())
                / (point3.getY() - point2.getY());
        return resultOnXPoint.equals(resultOnYPoint);
    }
}
