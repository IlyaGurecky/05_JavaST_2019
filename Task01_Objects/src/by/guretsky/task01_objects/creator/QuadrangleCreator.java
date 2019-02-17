package by.guretsky.task01_objects.creator;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import by.guretsky.task01_objects.exception.IncorrectQuadranglePointsException;
import by.guretsky.task01_objects.validator.QuadrangleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class QuadrangleCreator {
    private static final Logger LOGGER = LogManager
            .getLogger(QuadrangleCreator.class);

    public Quadrangle createQuadrangle(final List<Point> points) throws
            IncorrectQuadranglePointsException {
        QuadrangleValidator validator = new QuadrangleValidator();
        if (validator.isQuadrangle(points)) {
            return new Quadrangle(points);
        } else {
            LOGGER.error("You can't create quadrangle with this points:"
                    + " lie on the one line");
            throw new IncorrectQuadranglePointsException("Points in one line");
        }
    }
}
