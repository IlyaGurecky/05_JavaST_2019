package test.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Point;
import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.exception.IncorrectArgumentException;
import by.guretsky.task01_objects_b.exception.IncorrectQuadrangleDataException;
import by.guretsky.task01_objects_b.repository.QuadrangleRepositorySingleton;
import by.guretsky.task01_objects_b.repository.specification.FindByIdQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindByFirstPointXQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindByFirstPointYQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindBySecondPointXQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindBySecondPointYQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindByThirdPointXQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindByThirdPointYQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindByFourthPointXQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindByFourthPointYQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindByPerimeterQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindBySquareQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.FindQuadrangleSpecification;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for the find quadrangle specification.
 */
public class FindQuadrangleSpecificationTest {
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
     * The quadrangle for tests.
     */
    private Quadrangle quadrangle3;
    /**
     * The quadrangle for tests.
     */
    private Quadrangle quadrangle4;

    /**
     * The method for initialization fields.
     *
     * @throws IncorrectQuadrangleDataException if points can't
     *                                          create quadrangle
     * @throws IncorrectArgumentException       if argument is incorrect
     */
    @BeforeClass
    public void init() throws IncorrectQuadrangleDataException,
            IncorrectArgumentException {
        List<Point> points1 = new ArrayList<>(Arrays
                .asList(new Point(-1.0, 1.0),
                        new Point(1.0, 1.0),
                        new Point(1.0, -1.0),
                        new Point(-1.0, -1.0)));
        List<Point> points2 = new ArrayList<>(Arrays
                .asList(new Point(-3.0, 3.0),
                        new Point(3.0, 3.0),
                        new Point(3.0, -3.0),
                        new Point(-3.0, -3.0)));
        List<Point> points3 = new ArrayList<>(Arrays
                .asList(new Point(-5.0, 5.0),
                        new Point(5.0, 5.0),
                        new Point(5.0, -5.0),
                        new Point(-5.0, -5.0)));
        List<Point> points4 = new ArrayList<>(Arrays
                .asList(new Point(-6.0, 6.0),
                        new Point(6.0, 6.0),
                        new Point(6.0, -6.0),
                        new Point(-6.0, -6.0)));
        quadrangle1 = new Quadrangle(points1);
        quadrangle2 = new Quadrangle(points2);
        quadrangle3 = new Quadrangle(points3);
        quadrangle4 = new Quadrangle(points4);
        repository.addFigure(quadrangle1);
        repository.addFigure(quadrangle2);
        repository.addFigure(quadrangle3);
        repository.addFigure(quadrangle4);
    }

    /**
     * Data provider for findSpecifications test.
     *
     * @return Object[][]
     */
    @DataProvider(name = "data_for_find_specifications")
    public Object[][] createCorrectData1() {

        return new Object[][]{
                {new FindByFirstPointXQuadrangleSpecification(-3.0,
                        -1.0), Arrays.asList(quadrangle1, quadrangle2)},
                {new FindByFirstPointYQuadrangleSpecification(3.0,
                        6.0), Arrays.asList(quadrangle2, quadrangle3,
                        quadrangle4)},
                {new FindBySecondPointXQuadrangleSpecification(3.0,
                        5.0), Arrays.asList(quadrangle2, quadrangle3)},
                {new FindBySecondPointYQuadrangleSpecification(1.0,
                        5.0), Arrays.asList(quadrangle1, quadrangle2,
                        quadrangle3)},
                {new FindByThirdPointXQuadrangleSpecification(1.5,
                        3.5), Arrays.asList(quadrangle2)},
                {new FindByThirdPointYQuadrangleSpecification(-6.0,
                        -3.5), Arrays.asList(quadrangle3, quadrangle4)},
                {new FindByFourthPointXQuadrangleSpecification(-5.5,
                        -1.5), Arrays.asList(quadrangle2, quadrangle3)},
                {new FindByFourthPointYQuadrangleSpecification(-6.0,
                        -4.5), Arrays.asList(quadrangle3, quadrangle4)},
                {new FindByPerimeterQuadrangleSpecification(8.0,
                        24.0), Arrays.asList(quadrangle1, quadrangle2)},
                {new FindBySquareQuadrangleSpecification(37.0,
                        144.0), Arrays.asList(quadrangle3, quadrangle4)}
        };
    }

    /**
     * Test method for the different specifications.
     *
     * @param specification specification
     * @param expectedList  expected list
     */
    @Test(description = "Positive test method for find specification",
            dataProvider = "data_for_find_specifications")
    public void testFindSpecification(final FindQuadrangleSpecification
                                              specification,
                                      final List<Quadrangle> expectedList) {
        List<Quadrangle> actualList = repository.query(specification);
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * Test method for the FindById specification.
     */
    @Test(description = "Positive test for find by id method")
    public void testFindById() {
        FindByIdQuadrangleSpecification find =
                new FindByIdQuadrangleSpecification(2, 3);
        List<Quadrangle> expectedList = Arrays.asList(quadrangle3, quadrangle4);
        List<Quadrangle> actualList = repository.query(find);
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * The method clear repository.
     */
    @AfterClass
    public void clear() {
        repository.deleteAll();
    }
}
