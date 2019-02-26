package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Quadrangle;

import java.util.Comparator;

/**
 * Specification class. Used to sort quadrangles by square and figure ID.
 */
public class SortBySquareAndIdQuadrangleSpecification implements
        SortQuadrangleSpecification {

    /**
     * {@inheritDoc}.
     */
    @Override
    public Comparator<Quadrangle> specifiedComparator() {
        Comparator<Quadrangle> squareComparator = new
                SortBySquareQuadrangleSpecification().specifiedComparator();
        Comparator<Quadrangle> idComparator = new
                SortByIdQuadrangleSpecification().specifiedComparator();
        return squareComparator.thenComparing(idComparator);
    }
}
