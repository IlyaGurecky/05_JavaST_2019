package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Quadrangle;

/**
 * Specification class. Used to fined quadrangles by point range.
 */
public class FindByFourthPointXQuadrangleSpecification implements
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
    public FindByFourthPointXQuadrangleSpecification(final double lower,
                                                     final double upper) {
        lowerBorder = lower;
        upperBorder = upper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean specified(final Quadrangle quadrangle) {
        final int fourthPointIndex = 3;
        return quadrangle.getPoint(fourthPointIndex).getX() >= lowerBorder
                && quadrangle.getPoint(fourthPointIndex).getX() <= upperBorder;
    }
}
