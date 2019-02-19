package by.guretsky.task01_objects.factory;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import by.guretsky.task01_objects.exception.IncorrectQuadranglePointsException;
import by.guretsky.task01_objects.validator.QuadrangleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuadrangleFactoryImpl implements QuadrangleFactory {
    private static final Logger LOGGER = LogManager
            .getLogger(QuadrangleFactoryImpl.class);

    @Override
    public Quadrangle createQuadrangle(final List<Point> points) throws
            IncorrectQuadranglePointsException {
        QuadrangleValidator validator = new QuadrangleValidator();
        if (validator.isQuadrangle(points)) {
            return new Quadrangle(points);
        } else {
            LOGGER.error("You can't create quadrangle with this points:"
                    + " are on one line");
            throw new IncorrectQuadranglePointsException("Points in one line");
        }
    }

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
