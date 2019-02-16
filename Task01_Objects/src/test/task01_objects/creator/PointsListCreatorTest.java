package test.task01_objects.creator;

import by.guretsky.task01_objects.creator.PointsListCreator;
import by.guretsky.task01_objects.entity.PointForQuadrangle;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointsListCreatorTest {

    @Test(description = "Positive script for point creator")
    public void testCreatePoints() {
        List<Double> xy = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0,
                4.0, 5.0, 6.0, 7.0, 8.0));
        PointsListCreator creator = new PointsListCreator();
        List<PointForQuadrangle> expected = new ArrayList<>(Arrays
                .asList(new PointForQuadrangle(1.0, 2.0),
                        new PointForQuadrangle(3.0, 4.0),
                        new PointForQuadrangle(5.0, 6.0),
                        new PointForQuadrangle(7.0, 8.0)));
        List<PointForQuadrangle> actualPointsList = creator.createPoints(xy);

        Assert.assertEquals(actualPointsList, expected);
    }
}