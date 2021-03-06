package by.guretsky.task01_objects_b.registrator;

import by.guretsky.task01_objects_b.action.Calculator;
import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.observer.Observer;

/**
 * This class is used to calculate and store information about quadrangles.
 */
public class QuadrangleRecorder implements Observer {
    /**
     * ID of the objects.
     */
    private int id;
    /**
     * Square of the {@link Quadrangle}.
     */
    private double square;
    /**
     * Perimeter of the {@link Quadrangle}.
     */
    private double perimeter;

    /**
     * This method registers {@link Quadrangle}.
     *
     * @param quadrangle Quadrangle
     */
    public void register(final Quadrangle quadrangle) {
        id = quadrangle.getId();
        calculate(quadrangle);
    }

    /**
     * Get method for {@link QuadrangleRecorder#id}.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get method for square field.
     *
     * @return square
     */
    public double getSquare() {
        return square;
    }

    /**
     * Get method for perimeter field.
     *
     * @return perimeter
     */
    public double getPerimeter() {
        return perimeter;
    }

    /**
     * The method, which calculate quadrangle characteristics.
     *
     * @param quadrangle {@link Quadrangle}
     */
    private void calculate(final Quadrangle quadrangle) {
        Calculator calculator = new Calculator();
        square = calculator.square(quadrangle);
        perimeter = calculator.perimeter(quadrangle);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void update(final Object obj) {
        calculate((Quadrangle) obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "QuadrangleRecorder{" + "id=" + id + ", square=" + square
                + ", perimeter=" + perimeter + '}';
    }
}
