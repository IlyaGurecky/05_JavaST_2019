package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.registrator.QuadrangleRecorder;

/**
 * Specification class. Used to fined quadrangles by id range.
 */
public class FindByIdQuadrangleSpecification implements
        QuadrangleSpecification {
    /**
     * Lower border field.
     */
    private int lowerBorder;
    /**
     * Upper border field.
     */
    private int upperBorder;

    /**
     * Constructor with parameters.
     *
     * @param lower lower border
     * @param upper upper border
     */
    public FindByIdQuadrangleSpecification(final int lower,
                                           final int upper) {
        lowerBorder = lower;
        upperBorder = upper;
    }

    /**
     * This method check id of the quadrangles in specified range.
     *
     * @param recorder recorder, which you neew to check
     * @return true if id in right range
     */
    public boolean specified(final QuadrangleRecorder recorder) {

        return recorder.getId() >= lowerBorder
                && recorder.getId() <= upperBorder;
    }
}
