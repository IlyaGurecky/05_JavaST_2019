package by.guretsky.task01_objects.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quadrangle {
    private List<Point> points;

    private Quadrangle() {
    }

    public Quadrangle(final ArrayList<Point> pointsFromFile) {
        this.points = pointsFromFile;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(final List<Point> pointsFromFile) {
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
