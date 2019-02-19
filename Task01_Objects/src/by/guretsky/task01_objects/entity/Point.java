package by.guretsky.task01_objects.entity;

/**
 * This class used to store information about point.
 *
 * @author ilyaguretsky
 */
public class Point {
    /**
     * X coordinate.
     */
    private Double x;
    /**
     * Y coordinate.
     */
    private Double y;

    /**
     * Private constructor without parameters.
     *
     * @see Point#Point(Double, Double)
     */
    private Point() {
    }

    /**
     * Constructor - to create new object with special parameters.
     *
     * @param pointX X coordinate of the Point
     * @param pointY Y coordinate of the Point
     */
    public Point(final Double pointX, final Double pointY) {
        x = pointX;
        y = pointY;
    }

    /**
     * Function get the value {@link Point#x}.
     *
     * @return X coordinate
     */
    public Double getX() {
        return x;
    }

    /**
     * Set value of X coordinate, which you can get used the method
     * {@link Point#getX()}.
     *
     * @param pointX new value of the X coordinate
     */
    public void setX(final Double pointX) {
        this.x = pointX;
    }

    /**
     * Function get the value {@link Point#y}.
     *
     * @return Y coordinate
     */
    public Double getY() {
        return y;
    }

    /**
     * Set value of Y coordinate, which you can get used the method
     * {@link Point#getY()}.
     *
     * @param pointY new value of the Y coordinate
     */
    public void setY(final Double pointY) {
        this.y = pointY;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
