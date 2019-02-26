package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Quadrangle;

import java.util.Comparator;

/**
 * Specification class. Used to sort quadrangles by X and Y if X points are the
 * same.
 */
public class SortByFirstXAndYQuadrangleSpecification implements
        SortQuadrangleSpecification {

    /**
     * {@inheritDoc}.
     */
    @Override
    public Comparator<Quadrangle> specifiedComparator() {
        Comparator<Quadrangle> xComparator =
                new SortByFirstPointXQuadrangleSpecification()
                        .specifiedComparator();
        Comparator<Quadrangle> yComparator =
                new SortByFirstPointYQuadrangleSpecification()
                        .specifiedComparator();

        return xComparator.thenComparing(yComparator);
    }
}
