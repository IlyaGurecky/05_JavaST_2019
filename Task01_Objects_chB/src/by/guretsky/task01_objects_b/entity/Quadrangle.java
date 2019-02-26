package by.guretsky.task01_objects_b.entity;

import by.guretsky.task01_objects_b.exception.IncorrectQuadrangleDataException;
import by.guretsky.task01_objects_b.observer.Observable;
import by.guretsky.task01_objects_b.observer.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class used to store information about quadrangle.
 *
 * @author ilyaguretsky
 */
public class Quadrangle implements Geometry, Observable {
    /**
     * Logger, which used to log events.
     */
    private static final Logger LOGGER = LogManager.getLogger(Quadrangle.class);

    /**
     * Amount of points in quadrangle.
     */
    private static final int AMOUNT_OF_POINTS = 4;

    /**
     * This variable helps to calc object id.
     */
    private static int idCounter = 0;

    /**
     * Object id.
     */
    private int id;

    /**
     * List of the {@link Point}, which form a quadrangle.
     */
    private List<Point> points;

    /**
     * List of the observers.
     */
    private List<Observer> observers = new ArrayList<>();

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
     * @throws IncorrectQuadrangleDataException if amount of point is
     *                                          incorrect
     */
    public Quadrangle(final List<Point> pointsFromFile) throws
            IncorrectQuadrangleDataException {
        id = idCounter++;
        final int numberOfElements = 4;
        if (pointsFromFile.size() == numberOfElements) {
            this.points = pointsFromFile;
        } else {
            LOGGER.error("The number of points is not equal to 4");
            throw new IncorrectQuadrangleDataException("Incorrect amount "
                    + " of points");
        }
    }

    /**
     * Get method for id field.
     *
     * @return id
     */
    public int getId() {
        return id;
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
     * @throws IncorrectQuadrangleDataException if index is incorrect
     */
    public void setPointX(final int pointIndex, final Double x) throws
            IncorrectQuadrangleDataException {
        if (pointIndex < 0 || pointIndex >= AMOUNT_OF_POINTS) {
            LOGGER.error("Argument is incorrect");
            throw new IncorrectQuadrangleDataException("Incorrect argument");
        }
        this.points.get(pointIndex).setX(x);
    }

    /**
     * Set Y coordinate of the {@link Point} from {@link Quadrangle#points}.
     *
     * @param pointIndex index of the necessary point
     * @param y          new value of the X coordinate
     * @throws IncorrectQuadrangleDataException if index is incorrect
     */
    public void setPointY(final int pointIndex, final Double y) throws
            IncorrectQuadrangleDataException {
        if (pointIndex < 0 || pointIndex >= AMOUNT_OF_POINTS) {
            LOGGER.error("Argument is incorrect");
            throw new IncorrectQuadrangleDataException("Incorrect argument");
        }
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
        if (points == null) {
            result = prime * result;
        } else {
            result = prime * result + points.hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param observer observer we need to add
     */
    @Override
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    /**
     * {@inheritDoc}
     *
     * @param observer observer we need to add
     */
    @Override
    public void deleteObserver(final Observer observer) {
        observers.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
