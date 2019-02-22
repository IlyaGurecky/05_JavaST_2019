package by.guretsky.task01_objects.factory;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import by.guretsky.task01_objects.exception.IncorrectQuadranglePointsException;
import by.guretsky.task01_objects.validator.QuadrangleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Quadrangle factory. Include create methods for the one {@link Quadrangle}
 * object and for the list of quadrangles.
 */
public class QuadrangleFactoryImpl implements QuadrangleFactory {
    /**
     * Logger, which used to log events in the class.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(QuadrangleFactoryImpl.class);

    /**
     * {@inheritDoc}
     *
     * @param points list of the points, which form quadrangle
     * @return
     * @throws IncorrectQuadranglePointsException
     */
    @Override
    public Quadrangle createQuadrangle(final List<Point> points) throws
            IncorrectQuadranglePointsException {
        QuadrangleValidator validator = new QuadrangleValidator();
        if (validator.isQuadrangle(points)) {
            return new Quadrangle(points);
        } else {
            LOGGER.error("You can't create quadrangle with " + points
                    + " are on one line");
            throw new IncorrectQuadranglePointsException("Points in one line");
        }
    }

    /**
     * The method creates list of the quadrangle objects from the Map of
     * different coordinates.
     *
     * @param info Map of the coordinates
     * @return list of the quadrangle objects
     * @throws IncorrectQuadranglePointsException if points can't form
     *                                            quadrangle object
     */
    public List<Quadrangle> createQuadranglesList(final Map<Integer,
            List<Double>> info) throws IncorrectQuadranglePointsException {
        List<Quadrangle> quadrangles = new ArrayList<>();
        Set<Integer> keys = info.keySet();
        PointFactoryImpl pointsListCreator = new PointFactoryImpl();

        for (Integer index : keys) {
            List<Double> coordinates = info.get(index);
            List<Point> points = pointsListCreator
                    .createPointsList(coordinates);
            quadrangles.add(createQuadrangle(points));
        }
        return quadrangles;
    }
}
