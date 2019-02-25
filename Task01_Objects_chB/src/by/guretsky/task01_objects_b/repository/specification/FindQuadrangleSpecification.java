package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Quadrangle;

/**
 * Interface for classes with find rules.
 */
public interface FindQuadrangleSpecification extends QuadrangleSpecification {
    /**
     * This method is used to check the figure for some conditions.
     *
     * @param quadrangle checked figure
     * @return true if the figure satisfies the condition
     */
    boolean specified(Quadrangle quadrangle);
}
