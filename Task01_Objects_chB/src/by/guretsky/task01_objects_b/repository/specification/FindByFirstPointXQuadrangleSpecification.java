package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Quadrangle;

/**
 * Specification class. Used to fined quadrangles by point range.
 */
public class FindByFirstPointXQuadrangleSpecification implements
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
    public FindByFirstPointXQuadrangleSpecification(final double lower,
                                                    final double upper) {
        lowerBorder = lower;
        upperBorder = upper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean specified(final Quadrangle quadrangle) {
        return quadrangle.getPoint(0).getX() >= lowerBorder
                && quadrangle.getPoint(0).getX() <= upperBorder;
    }
}
