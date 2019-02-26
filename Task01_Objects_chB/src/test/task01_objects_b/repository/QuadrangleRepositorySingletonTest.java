package test.task01_objects_b.repository;

import by.guretsky.task01_objects_b.entity.Point;
import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.exception.IncorrectArgumentException;
import by.guretsky.task01_objects_b.exception.IncorrectQuadrangleDataException;
import by.guretsky.task01_objects_b.registrator.QuadrangleRecorder;
import by.guretsky.task01_objects_b.repository.QuadrangleRepositorySingleton;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for {@link QuadrangleRepositorySingleton}.
 */
public class QuadrangleRepositorySingletonTest {
    /**
     * Repository object.
     */
    private QuadrangleRepositorySingleton repository =
            QuadrangleRepositorySingleton.getInstance();
    /**
     * The quadrangle for tests.
     */
    private Quadrangle quadrangle1;
    /**
     * The quadrangle for tests.
     */
    private Quadrangle quadrangle2;

    /**
     * The method for initialization fields.
     *
     * @throws IncorrectQuadrangleDataException if arguments is incorrect
     */
    @BeforeClass
    public void init() throws IncorrectQuadrangleDataException {
        List<Point> points1 = new ArrayList<>(Arrays
                .asList(new Point(-2.0, 2.0),
                        new Point(2.0, 2.0),
                        new Point(2.0, -2.0),
                        new Point(-2.0, -2.0)));
        List<Point> points2 = new ArrayList<>(Arrays
                .asList(new Point(-4.0, 4.0),
                        new Point(4.0, 4.0),
                        new Point(4.0, -4.0),
                        new Point(-4.0, -4.0)));
        quadrangle1 = new Quadrangle(points1);
        quadrangle2 = new Quadrangle(points2);
    }

    /**
     * Clear repository after test method.
     */
    @AfterMethod
    public void clearRepository() {
        repository.deleteAll();
    }

    /**
     * Positive test method for
     * {@link QuadrangleRepositorySingleton#addFigure(Quadrangle)}.
     *
     * @throws IncorrectArgumentException if arguments is incorrect
     */
    @Test(description = "Positive script for the add method")
    public void testAddFigure() throws IncorrectArgumentException {
        List<Quadrangle> expectedList = new ArrayList<>(Arrays
                .asList(quadrangle1, quadrangle2));
        repository.addFigure(quadrangle1);
        repository.addFigure(quadrangle2);
        List<Quadrangle> actualList = repository.getQuadrangles();
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * Negative test method for
     * {@link QuadrangleRepositorySingleton#addFigure(Quadrangle)}.
     *
     * @throws IncorrectArgumentException if arguments is incorrect
     */
    @Test(description = "Negative script for the add method",
            expectedExceptions = IncorrectArgumentException.class)
    public void testAddFigure2() throws IncorrectArgumentException {
        List<Quadrangle> expectedList = new ArrayList<>(Arrays
                .asList(quadrangle1, quadrangle2));

        repository.addFigure(null);
        repository.addFigure(quadrangle2);
        List<Quadrangle> actualList = repository.getQuadrangles();
        Assert.assertEquals(actualList, expectedList);
    }


    /**
     * Positive test method for
     * {@link QuadrangleRepositorySingleton#deleteFigure(Quadrangle)}.
     *
     * @throws IncorrectArgumentException if arguments is incorrect
     */
    @Test(description = "Positive script for the delete method")
    public void testDeleteFigure() throws IncorrectArgumentException {
        List<Quadrangle> expectedList = new ArrayList<>(Arrays
                .asList(quadrangle2));

        repository.addFigure(quadrangle1);
        repository.addFigure(quadrangle2);
        repository.deleteFigure(quadrangle1);
        List<Quadrangle> actualList = repository.getQuadrangles();
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * Negative test method for
     * {@link QuadrangleRepositorySingleton#deleteFigure(Quadrangle)}.
     *
     * @throws IncorrectArgumentException if arguments is incorrect
     */
    @Test(description = "Negative script for the delete method",
            expectedExceptions = IncorrectArgumentException.class)
    public void testDeleteFigure2() throws IncorrectArgumentException {
        List<Quadrangle> expectedList = new ArrayList<>(Arrays
                .asList(quadrangle2));

        repository.addFigure(null);
        repository.deleteFigure(quadrangle1);
        List<Quadrangle> actualList = repository.getQuadrangles();
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * Positive test method for
     * {@link QuadrangleRepositorySingleton#deleteFigure(int)}.
     *
     * @throws IncorrectArgumentException if arguments is incorrect
     */
    @Test(description = "Positive script for the delete method")
    public void testDeleteFigure1() throws IncorrectArgumentException {
        List<Quadrangle> expectedList = new ArrayList<>(Arrays
                .asList(quadrangle2));

        repository.addFigure(quadrangle1);
        repository.addFigure(quadrangle2);
        repository.deleteFigure(0);
        List<Quadrangle> actualList = repository.getQuadrangles();
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * Negative test method for
     * {@link QuadrangleRepositorySingleton#deleteFigure(int)}.
     *
     * @throws IncorrectArgumentException if arguments is incorrect
     */
    @Test(description = "Negative script for the delete method",
            expectedExceptions = IncorrectArgumentException.class)
    public void testDeleteFigure3() throws IncorrectArgumentException {
        List<Quadrangle> expectedList = new ArrayList<>(Arrays
                .asList(quadrangle1));
        repository.deleteFigure(1);
        List<Quadrangle> actualList = repository.getQuadrangles();
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * Positive test method for {@link QuadrangleRepositorySingleton#
     * changeQuadranglePointX(int, int, double)}.
     *
     * @throws IncorrectArgumentException       if arguments is incorrect
     * @throws IncorrectQuadrangleDataException if number of point < 0 or
     *                                          bigger than 4
     */
    @Test(description = "Positive script for the change point x method")
    public void testChangeQuadranglePointX() throws IncorrectArgumentException,
            IncorrectQuadrangleDataException {
        repository.addFigure(quadrangle1);
        repository.addFigure(quadrangle2);
        repository.changeQuadranglePointX(0, 0,
                -8.0);
        List<QuadrangleRecorder> actualRecorders = repository.getRecorders();
        final double expectedSquare = 28.0;
        Assert.assertEquals(actualRecorders.get(0).getSquare(), expectedSquare);
    }

    /**
     * Positive test method for
     * {@link QuadrangleRepositorySingleton#changeQuadranglePointY(int,
     * int, double)}.
     *
     * @throws IncorrectArgumentException       if arguments is incorrect
     * @throws IncorrectQuadrangleDataException if number of point < 0 or
     *                                          bigger than 4
     */
    @Test(description = "Positive script for the change point y method",
    dependsOnMethods = {"testChangeQuadranglePointX"})
    public void testChangeQuadranglePointY() throws IncorrectArgumentException,
            IncorrectQuadrangleDataException {
        repository.addFigure(quadrangle1);
        repository.changeQuadranglePointY(0, 0,
                4.0);
        List<QuadrangleRecorder> actualRecorders = repository.getRecorders();
        final double expectedSquare = 32.0;
        Assert.assertEquals(actualRecorders.get(0).getSquare(), expectedSquare);
    }

    /**
     * Negative test method for
     * {@link QuadrangleRepositorySingleton#changeQuadranglePointX(int,
     * int, double)}.
     *
     * @throws IncorrectArgumentException       if arguments is incorrect
     * @throws IncorrectQuadrangleDataException if number of point < 0 or
     *                                          bigger than 4
     */
    @Test(description = "Negative script for the change point x method",
            expectedExceptions = IncorrectArgumentException.class)
    public void testChangeQuadranglePointX1() throws
            IncorrectArgumentException, IncorrectQuadrangleDataException {
        repository.changeQuadranglePointX(2, 0,
                -8.0);
        List<QuadrangleRecorder> actualRecorders = repository.getRecorders();
        final double expectedSquare = 28.0;
        Assert.assertEquals(actualRecorders.get(0).getSquare(), expectedSquare);
    }

    /**
     * Negative test method for
     * {@link QuadrangleRepositorySingleton#changeQuadranglePointY(int,
     * int, double)}.
     *
     * @throws IncorrectArgumentException       if arguments is incorrect
     * @throws IncorrectQuadrangleDataException if number of point < 0 or
     *                                          bigger than 4
     */
    @Test(description = "Negative script for the change point y method",
            expectedExceptions = IncorrectArgumentException.class)
    public void testChangeQuadranglePointY1() throws
            IncorrectArgumentException, IncorrectQuadrangleDataException {
        repository.changeQuadranglePointY(-1, 0,
                4.0);
        List<QuadrangleRecorder> actualRecorders = repository.getRecorders();
        final double expectedSquare = 32.0;
        Assert.assertEquals(actualRecorders.get(0).getSquare(), expectedSquare);
    }

    /**
     * The method clear repository.
     */
    @AfterClass
    public void clear() {
        repository.deleteAll();
    }
}
