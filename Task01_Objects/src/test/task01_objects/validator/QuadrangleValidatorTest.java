package test.task01_objects.validator;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.validator.QuadrangleValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for {@link QuadrangleValidator}.
 */
public class QuadrangleValidatorTest {
    /**
     * The list of the {@link Point} objects, which form the
     * {@link by.guretsky.task01_objects.entity.Quadrangle}.
     */
    private List<Point> pointsList;

    /**
     * Positive test method for {@link QuadrangleValidator#isQuadrangle(List)}.
     */
    @Test(description = "Positive script for the fact that our"
            + " figure is quadrangle")
    public void testIsQuadrangle() {
        pointsList = new ArrayList<>(Arrays
                .asList(new Point(-1.0, 0.0),
                        new Point(-1.0, -1.0),
                        new Point(1.0, 1.0),
                        new Point(0.0, 1.0)));
        QuadrangleValidator validator = new QuadrangleValidator();
        boolean isQuadrangle = validator.isQuadrangle(pointsList);
        Assert.assertTrue(isQuadrangle);
    }

    /**
     * Negative test method for {@link QuadrangleValidator#isQuadrangle(List)}.
     */
    @Test(description = "Negative script for the fact that our"
            + " figure is quadrangle")
    public void testIsNotQuadrangle() {
        pointsList = new ArrayList<>(Arrays
                .asList(new Point(-0.44, 1.56),
                        new Point(0.96345, 2.96345),
                        new Point(2.224, 4.224),
                        new Point(0.1963, 2.1963)));
        QuadrangleValidator validator = new QuadrangleValidator();
        boolean isQuadrangle = validator.isQuadrangle(pointsList);
        Assert.assertFalse(isQuadrangle);
    }
}
