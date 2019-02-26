package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Quadrangle;

import java.util.Comparator;

/**
 * Specification class. Used to sort quadrangles by ID.
 */
public class SortByIdQuadrangleSpecification implements
        SortQuadrangleSpecification {

    /**
     * {@inheritDoc}.
     */
    @Override
    public Comparator<Quadrangle> specifiedComparator() {
        return Comparator.comparing(Quadrangle::getId);
    }
}
