package by.guretsky.task01_objects.entity;

import java.util.List;
import java.util.Objects;

public class Quadrangle {
    private List<PointForQuadrangle> points;

    private Quadrangle() {
    }

    public Quadrangle(final List<PointForQuadrangle> pointsFromFile) {
        this.points = pointsFromFile;
    }

    public List<PointForQuadrangle> getPoints() {
        return points;
    }

    public void setPoints(final List<PointForQuadrangle> pointsFromFile) {
        this.points = pointsFromFile;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Quadrangle that = (Quadrangle) o;
        return Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }
}
