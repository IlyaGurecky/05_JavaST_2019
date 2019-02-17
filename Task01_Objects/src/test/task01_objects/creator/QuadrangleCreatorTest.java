package test.task01_objects.creator;

import by.guretsky.task01_objects.creator.QuadrangleCreator;
import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import by.guretsky.task01_objects.exception.IncorrectQuadranglePointsException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

@SuppressWarnings("CheckStyle")
public class QuadrangleCreatorTest {
    private final QuadrangleCreator creator = new QuadrangleCreator();


    @Test(description = "Positive script for the creator")
    public void testCreateQuadrangle1() {

    }

    @Test(expectedExceptions = IncorrectQuadranglePointsException.class,
            description = "Negative script for the creator")
    public void testCreateQuadrangle2() throws
            IncorrectQuadranglePointsException {
        List<Point> points = new ArrayList<>(Arrays
                .asList(new Point(-1.0, 0.0),
                        new Point(0.0, 1.0),
                        new Point(1.0, 2.0),
                        new Point(2.0, 0.0)));
        Quadrangle actualQuadrangle = creator.createQuadrangle(points);
        Quadrangle expectedQuadrangle = new Quadrangle(new ArrayList<>());
        Assert.assertEquals(actualQuadrangle, expectedQuadrangle);
    }

}