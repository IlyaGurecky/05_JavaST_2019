package by.guretsky.task01_objects.entity;

import java.util.Objects;

public class Point {
    private Double x;
    private Double y;

    private Point() {
    }

    public Point(final Double pointX, final Double pointY) {
        x = pointX;
        y = pointY;
    }

    public Double getX() {
        return x;
    }

    public void setX(final Double pointX) {
        this.x = pointX;
    }

    public Double getY() {
        return y;
    }

    public void setY(final Double pointY) {
        this.y = pointY;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(x, point.x) && Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
