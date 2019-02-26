package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.registrator.QuadrangleRecorder;

/**
 * Specification class. Used to fined quadrangles by perimeter range.
 */
public class FindByPerimeterQuadrangleSpecification implements
        FindByQuadrangleCharacteristics {
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
     *
     * @param obj figure recorder
     */
    @Override
    public boolean specified(final Object obj) {
        return ((QuadrangleRecorder) obj).getPerimeter() >= lowerBorder
                && ((QuadrangleRecorder) obj).getPerimeter() <= upperBorder;
    }
}
