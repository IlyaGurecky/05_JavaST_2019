package test.task01_objects.factory;

import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import by.guretsky.task01_objects.exception.IncorrectQuadrangleDataException;
import by.guretsky.task01_objects.factory.QuadrangleFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for {@link QuadrangleFactoryImpl}.
 */
public class QuadrangleFactoryImplTest {
    /**
     * {@link QuadrangleFactoryImpl} object.
     */
    private final QuadrangleFactoryImpl creator = new QuadrangleFactoryImpl();

    /**
     * Positive test method for
     * {@link QuadrangleFactoryImpl#createQuadrangle(List)}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Positive script for the factory")
    public void testCreateQuadrangle1() throws
            IncorrectQuadrangleDataException {
        List<Point> points = new ArrayList<>(Arrays
                .asList(new Point(-2.0, 0.0),
                        new Point(0.0, 1.0),
                        new Point(1.0, 3.0),
                        new Point(4.0, 0.0)));
        Quadrangle actualQuadrangle = creator.createQuadrangle(points);
        Quadrangle expectedQuadrangle = new Quadrangle(points);
        Assert.assertEquals(actualQuadrangle, expectedQuadrangle);
    }

    /**
     * Negative test method for
     * {@link QuadrangleFactoryImpl#createQuadrangle(List)}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(expectedExceptions = IncorrectQuadrangleDataException.class,
            description = "Negative script for the factory")
    public void testCreateQuadrangle2() throws
            IncorrectQuadrangleDataException {
        List<Point> points = new ArrayList<>(Arrays
                .asList(new Point(-1.0, 0.0),
                        new Point(0.0, 1.0),
                        new Point(1.0, 2.0),
                        new Point(2.0, 0.0)));
        Quadrangle actualQuadrangle = creator.createQuadrangle(points);
        Quadrangle expectedQuadrangle = new Quadrangle(new ArrayList<>());
        Assert.assertEquals(actualQuadrangle, expectedQuadrangle);
    }

    /**
     * Positive test method for
     * {@link QuadrangleFactoryImpl#createQuadranglesList(Map)} (List)}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Positive script for the factory")
    public void testCreateQuadrangleList1() throws
            IncorrectQuadrangleDataException {
        List<Point> pointList1 = new ArrayList<>(Arrays
                .asList(new Point(1.0, 2.0),
                        new Point(-3.0, 3.0),
                        new Point(-3.0, -4.0),
                        new Point(5.0, -3.0)));
        List<Point> pointList2 = new ArrayList<>(Arrays
                .asList(new Point(1.0, 2.0),
                        new Point(-3.0, 3.0),
                        new Point(-1.0, -1.0),
                        new Point(1.0, -1.0)));
        Map<Integer, List<Double>> coordinates = new HashMap<>();

        coordinates.put(1, new ArrayList<>(Arrays.asList(1.0, 2.0, -3.0, 3.0,
                -3.0, -4.0, 5.0, -3.0)));
        coordinates.put(2, new ArrayList<>(Arrays.asList(1.0, 2.0, -3.0, 3.0,
                -1.0, -1.0, 1.0, -1.0)));

        List<Quadrangle> actualQuadranglesList = creator
                .createQuadranglesList(coordinates);
        List<Quadrangle> expectedQuadranglesList = new ArrayList<>(Arrays
                .asList(new Quadrangle(pointList1),
                        new Quadrangle(pointList2)));
        Assert.assertEquals(actualQuadranglesList, expectedQuadranglesList);
    }

    /**
     * Negative test method for the
     * {@link QuadrangleFactoryImpl#createQuadranglesList(Map)} (List)}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Negative script for the factory")
    public void testCreateQuadrangleList2() throws
            IncorrectQuadrangleDataException {
        List<Point> pointList1 = new ArrayList<>(Arrays
                .asList(new Point(1.0, 2.0),
                        new Point(-3.0, 3.0),
                        new Point(-3.0, -4.0),
                        new Point(5.0, -3.0)));
        List<Point> pointList2 = new ArrayList<>(Arrays
                .asList(new Point(-3.0, 1.0),
                        new Point(-2.0, 1.0),
                        new Point(4.0, 1.0),
                        new Point(1.0, -1.0)));
        List<Quadrangle> expectedList = new ArrayList<>(Arrays
                .asList(new Quadrangle(pointList1),
                        new Quadrangle(pointList2)));
        Map<Integer, List<Double>> coordinates = new HashMap<>();
        coordinates.put(1, new ArrayList<>(Arrays.asList(1.0, 2.0, -3.0, 3.0,
                -3.0, -4.0, 5.0, -3.0)));
        coordinates.put(2, new ArrayList<>(Arrays.asList(-3.0, 1.0, -2.0, 1.0,
                4.0, 1.0, 1.0, -1.0)));
        List<Quadrangle> actualList = creator
                .createQuadranglesList(coordinates);
        Assert.assertEquals(actualList, expectedList);
    }
}
