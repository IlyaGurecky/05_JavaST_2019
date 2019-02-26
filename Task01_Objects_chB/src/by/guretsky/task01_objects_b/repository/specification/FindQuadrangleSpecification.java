package by.guretsky.task01_objects_b.repository.specification;

/**
 * Interface for classes with find rules.
 */
public interface FindQuadrangleSpecification extends QuadrangleSpecification {
    /**
     * This method is used to check the figure for some conditions.
     *
     * @param obj checked figure
     * @return true if the figure satisfies the condition
     */
    boolean specified(Object obj);
}
