package by.guretsky.task01_objects.entity;

import java.util.List;

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
        return points.equals(that.points);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((points == null) ? 0 : points.hashCode());
        return result;
    }
}
