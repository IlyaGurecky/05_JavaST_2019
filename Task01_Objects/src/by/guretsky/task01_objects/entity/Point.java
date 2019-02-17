package by.guretsky.task01_objects.entity;


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
        return x.equals(point.x)
                && y.equals(point.y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
