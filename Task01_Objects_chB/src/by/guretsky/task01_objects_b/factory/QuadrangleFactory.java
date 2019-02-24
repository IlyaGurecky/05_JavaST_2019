package by.guretsky.task01_objects_b.factory;

import by.guretsky.task01_objects_b.entity.Point;
import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.exception.IncorrectQuadrangleDataException;

import java.util.List;

/**
 * Interface for quadrangle factory.
 */
public interface QuadrangleFactory extends Factory<Quadrangle> {
    /**
     * The method validates list of the {@link Point} and
     * create {@link Quadrangle} object.
     *
     * @param points list of the points, which form quadrangle
     * @return {@link Quadrangle} object
     * @throws IncorrectQuadrangleDataException if points can't form
     *                                            Quadrangle object
     */
    Quadrangle createQuadrangle(List<Point> points) throws
            IncorrectQuadrangleDataException;
}
