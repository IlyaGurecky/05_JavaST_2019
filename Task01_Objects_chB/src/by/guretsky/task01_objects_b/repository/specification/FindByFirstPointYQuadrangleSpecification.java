package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Quadrangle;

/**
 * Specification class. Used to fined quadrangles by point range.
 */
public class FindByFirstPointYQuadrangleSpecification implements
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
    public FindByFirstPointYQuadrangleSpecification(final double lower,
                                                    final double upper) {
        lowerBorder = lower;
        upperBorder = upper;
    }

    /**
     * {@inheritDoc}
     *
     * @param obj
     */
    @Override
    public boolean specified(final Object obj) {
        return ((Quadrangle) obj).getPoint(0).getY() >= lowerBorder
                && ((Quadrangle) obj).getPoint(0).getY() <= upperBorder;
    }
}
