package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Quadrangle;

import java.util.Comparator;

/**
 * Specification class. Used to sort quadrangles by third point X and figure ID.
 */
public class SortByThirdPointXAndIDQuadrangleSpecification implements
        SortQuadrangleSpecification {
    /**
     * {@inheritDoc}.
     */
    @Override
    public Comparator<Quadrangle> specifiedComparator() {
        Comparator<Quadrangle> xComparator = new
                SortByThirdPointXQuadrangleSpecification()
                .specifiedComparator();
        Comparator<Quadrangle> idComparator = new
                SortByIdQuadrangleSpecification()
                .specifiedComparator();

        return xComparator.thenComparing(idComparator);
    }
}
