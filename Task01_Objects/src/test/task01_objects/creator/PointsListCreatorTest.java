package test.task01_objects.creator;

import by.guretsky.task01_objects.creator.PointsListCreator;
import by.guretsky.task01_objects.entity.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("CheckStyle")
public class PointsListCreatorTest {

    @Test(description = "Positive script for point creator")
    public void testCreatePoints() {
        List<Double> xy = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0,
                6.0, 7.0, 8.0));
        PointsListCreator creator = new PointsListCreator();
        List<Point> expected = new ArrayList<>(Arrays
                .asList(new Point(1.0, 2.0),
                        new Point(3.0, 4.0),
                        new Point(5.0, 6.0),
                        new Point(7.0, 8.0)));
        List<Point> actualPointsList = creator.createPoints(xy);

        Assert.assertEquals(actualPointsList, expected);
    }
}