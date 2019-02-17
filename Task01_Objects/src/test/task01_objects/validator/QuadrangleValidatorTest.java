package test.task01_objects.validator;

import by.guretsky.task01_objects.entity.PointForQuadrangle;
import by.guretsky.task01_objects.validator.QuadrangleValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("CheckStyle")
public class QuadrangleValidatorTest {
    private List<PointForQuadrangle> pointsList;

    @Test(description = "Positive script for the fact that our"
            + " figure is quadrangle")
    public void testIsQuadrangle() {
        pointsList = new ArrayList<>(Arrays
                .asList(new PointForQuadrangle(-1.0, 0.0),
                        new PointForQuadrangle(-1.0, -1.0),
                        new PointForQuadrangle(1.0, 1.0),
                        new PointForQuadrangle(0.0, 1.0)));
        QuadrangleValidator validator = new QuadrangleValidator();
        boolean isQuadrangle = validator.isQuadrangle(pointsList);
        Assert.assertTrue(isQuadrangle);
    }

    @Test(description = "Negative script for the fact that our"
            + " figure is quadrangle")
    public void testIsNotQuadrangle() {
        pointsList = new ArrayList<>(Arrays
                .asList(new PointForQuadrangle(-0.44, 1.56),
                        new PointForQuadrangle(0.96345, 2.96345),
                        new PointForQuadrangle(2.224, 4.224),
                        new PointForQuadrangle(0.1963, 2.1963)));
        QuadrangleValidator validator = new QuadrangleValidator();
        boolean isQuadrangle = validator.isQuadrangle(pointsList);
        Assert.assertFalse(isQuadrangle);
    }
}