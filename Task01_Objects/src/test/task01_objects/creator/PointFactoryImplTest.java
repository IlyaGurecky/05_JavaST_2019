package test.task01_objects.creator;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.factory.PointFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("CheckStyle")
public class PointFactoryImplTest {

    @Test(description = "Positive script for point list creator")
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