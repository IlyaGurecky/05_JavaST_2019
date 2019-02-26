package by.guretsky.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.action.Calculator;
import by.guretsky.task01_objects_b.entity.Quadrangle;

import java.util.Comparator;

/**
 * Specification class. Used to sort quadrangles by Perimeter.
 */
public class SortByPerimeterQuadrangleSpecification implements
        SortQuadrangleSpecification {

    /**
     * {@inheritDoc}.
     */
    @Override
    public Comparator<Quadrangle> specifiedComparator() {
        return Comparator
                .comparingDouble(value -> new Calculator().perimeter(value));
    }
}
