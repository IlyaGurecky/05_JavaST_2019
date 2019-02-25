package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.action.Calculator;
import by.guretsky.task01_objects_b.entity.Quadrangle;

/**
 * Specification class. Used to fined quadrangles by square range.
 */
public class FindBySquareQuadrangleSpecification implements
        FindQuadrangleSpecification {
    /**
     * Lower border field.
     */
    private double lowerBorder;
    /**
     * Upper border field.
     */
    private double upperBorder;

    /**
     * Constructor with parameters.
     *
     * @param lower lower border
     * @param upper upper border
     */
    public FindBySquareQuadrangleSpecification(final double lower,
                                               final double upper) {
        lowerBorder = lower;
        upperBorder = upper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean specified(final Quadrangle quadrangle) {
        Calculator calculator = new Calculator();
        Double square = calculator.square(quadrangle);

        return square >= lowerBorder && square <= upperBorder;
    }
}
