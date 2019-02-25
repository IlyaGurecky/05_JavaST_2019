package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Quadrangle;

import java.util.Comparator;

/**
 * Interface for classes with sorted rules.
 */
public interface SortQuadrangleSpecification extends QuadrangleSpecification,
        Comparator<Object> {
    /**
     * This method is used to define sorting rules.
     *
     * @return quadrangle comparator
     */
    Comparator<Quadrangle> specifiedComparator();
}
