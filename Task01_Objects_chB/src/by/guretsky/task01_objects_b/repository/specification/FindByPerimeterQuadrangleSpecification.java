package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.action.Calculator;
import by.guretsky.task01_objects_b.entity.Quadrangle;

/**
 * Specification class. Used to fined quadrangles by perimeter range.
 */
public class FindByPerimeterQuadrangleSpecification implements
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
    public FindByPerimeterQuadrangleSpecification(final double lower,
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
        Double perimeter = calculator.perimeter(quadrangle);

        return perimeter >= lowerBorder && perimeter <= upperBorder;
    }
}
