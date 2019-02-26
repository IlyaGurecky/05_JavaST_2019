package test.task01_objects_b.repository.specification;

import by.guretsky.task01_objects_b.entity.Point;
import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.exception.IncorrectArgumentException;
import by.guretsky.task01_objects_b.exception.IncorrectQuadrangleDataException;
import by.guretsky.task01_objects_b.repository.QuadrangleRepositorySingleton;

import by.guretsky.task01_objects_b.repository.specification.SortByFirstPointXQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortByFirstPointYQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortByFirstXAndYQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortByIdQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortByPerimeterQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortBySecondPointXQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortBySecondPointYQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortBySquareAndIdQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortBySquareQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortByThirdPointXAndIDQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortByThirdPointXQuadrangleSpecification;
import by.guretsky.task01_objects_b.repository.specification.SortQuadrangleSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for the sort quadrangle specification.
 */
public class SortQuadrangleSpecificationTest {
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
                .asList(new Point(-2.0, 2.0),
                        new Point(2.0, 2.0),
                        new Point(3.0, -2.0),
                        new Point(-2.0, -2.0)));
        List<Point> points2 = new ArrayList<>(Arrays
                .asList(new Point(-3.0, 3.0),
                        new Point(3.0, 3.0),
                        new Point(3.0, -3.0),
                        new Point(-3.0, -3.0)));
        quadrangle2 = new Quadrangle(points2);
        repository.addFigure(quadrangle2);
        List<Point> points3 = new ArrayList<>(Arrays
                .asList(new Point(-7.0, 7.0),
                        new Point(7.0, 7.0),
                        new Point(7.0, -7.0),
                        new Point(-7.0, -7.0)));
        quadrangle3 = new Quadrangle(points3);
        repository.addFigure(quadrangle3);
        List<Point> points4 = new ArrayList<>(Arrays
                .asList(new Point(-8.0, 8.0),
                        new Point(8.0, 8.0),
                        new Point(8.0, -8.0),
                        new Point(-8.0, -8.0)));
        quadrangle4 = new Quadrangle(points4);
        quadrangle1 = new Quadrangle(points1);
        repository.addFigure(quadrangle4);
        repository.addFigure(quadrangle1);
    }

    /**
     * Data provider for sortSpecifications test.
     *
     * @return Object[][]
     */
    @DataProvider(name = "data_for_sort_specifications")
    public Object[][] createCorrectData() {

        return new Object[][]{
                {new SortByFirstPointXQuadrangleSpecification(),
                        Arrays.asList(quadrangle4, quadrangle3,
                                quadrangle2, quadrangle1)},
                {new SortByFirstPointYQuadrangleSpecification(),
                        Arrays.asList(quadrangle1, quadrangle2,
                                quadrangle3, quadrangle4)},
                {new SortByIdQuadrangleSpecification(),
                        Arrays.asList(quadrangle2, quadrangle3,
                                quadrangle4, quadrangle1)},
                {new SortByPerimeterQuadrangleSpecification(),
                        Arrays.asList(quadrangle1, quadrangle2,
                                quadrangle3, quadrangle4)},
                {new SortBySecondPointXQuadrangleSpecification(),
                        Arrays.asList(quadrangle1, quadrangle2,
                                quadrangle3, quadrangle4)},
                {new SortBySecondPointYQuadrangleSpecification(),
                        Arrays.asList(quadrangle1, quadrangle2,
                                quadrangle3, quadrangle4)},
                {new SortBySquareQuadrangleSpecification(),
                        Arrays.asList(quadrangle1, quadrangle2,
                                quadrangle3, quadrangle4)},
                {new SortByFirstXAndYQuadrangleSpecification(),
                        Arrays.asList(quadrangle4, quadrangle3,
                                quadrangle2, quadrangle1)},
                {new SortByThirdPointXQuadrangleSpecification(),
                        Arrays.asList(quadrangle2, quadrangle1,
                                quadrangle3, quadrangle4)},
                {new SortByThirdPointXAndIDQuadrangleSpecification(),
                        Arrays.asList(quadrangle2, quadrangle1,
                                quadrangle3, quadrangle4)},
                {new SortBySquareAndIdQuadrangleSpecification(),
                        Arrays.asList(quadrangle1, quadrangle2,
                                quadrangle3, quadrangle4)}
        };
    }

    /**
     * Test method for the different specifications.
     *
     * @param specification specification
     * @param expectedList  expected list
     */
    @Test(description = "Positive test method for sort specification",
            dataProvider = "data_for_sort_specifications")
    public void testSortSpecification(final SortQuadrangleSpecification
                                              specification,
                                      final List<Quadrangle> expectedList) {
        List<Quadrangle> actualList = repository.query(specification);
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * The method clear repository after tests.
     */
    @AfterClass
    public void clear() {
        repository.deleteAll();
    }
}
