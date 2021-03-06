package test.task01_objects.factory;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.factory.PointFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for {@link PointFactoryImpl}.
 */
public class PointFactoryImplTest {
    /**
     * Positive test method for {@link PointFactoryImpl#createPointsList(List)}.
     */
    @Test(description = "Positive script for point list factory")
    public void testCreatePoints() {
        List<Double> xy = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0));
        PointFactoryImpl creator = new PointFactoryImpl();
        List<Point> expected = new ArrayList<>(Arrays
                .asList(new Point(1.0, 2.0),
                        new Point(3.0, 4.0),
                        new Point(5.0, 6.0),
                        new Point(7.0, 8.0)));
        List<Point> actualPointsList = creator.createPointsList(xy);
        Assert.assertEquals(actualPointsList, expected);
    }
}
