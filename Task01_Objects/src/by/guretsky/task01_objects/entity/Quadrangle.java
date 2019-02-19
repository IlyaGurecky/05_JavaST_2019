package by.guretsky.task01_objects.entity;

import java.util.List;

/**
 * This class used to store information about quadrangle.
 *
 * @author ilyaguretsky
 */
public class Quadrangle {
    /**
     * List of the {@link Point}, which form a quadrangle.
     */
    private List<Point> points;

    /**
     * Private constructor without parameters.
     *
     * @see Quadrangle#Quadrangle(List)
     */
    private Quadrangle() {
    }

    /**
     * Constructor - to create new object with special parameter.
     *
     * @param pointsFromFile list of the {@link Point}
     */
    public Quadrangle(final List<Point> pointsFromFile) {
        this.points = pointsFromFile;
    }

    /**
     * Function get the value {@link Point} from the {@link Quadrangle#points}.
     *
     * @param index number of the point
     * @return one {@link Point} according to the index
     */
    public Point getPoint(final int index) {
        return points.get(index);
    }

    /**
     * Set X coordinate of the {@link Point} from {@link Quadrangle#points}.
     *
     * @param pointIndex index of the necessary point
     * @param x          new value of the X coordinate
     */
    public void setPointX(final int pointIndex, final Double x) {
        this.points.get(pointIndex).setX(x);
    }

    /**
     * Set Y coordinate of the {@link Point} from {@link Quadrangle#points}.
     *
     * @param pointIndex index of the necessary point
     * @param y          new value of the X coordinate
     */
    public void setPointY(final int pointIndex, final Double y) {
        this.points.get(pointIndex).setY(y);
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
        Quadrangle that = (Quadrangle) o;
        return points.equals(that.points);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((points == null) ? 0 : points.hashCode());
        return result;
    }
}
