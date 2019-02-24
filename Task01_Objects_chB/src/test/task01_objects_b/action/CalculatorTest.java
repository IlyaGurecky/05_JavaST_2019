package test.task01_objects_b.action;

import by.guretsky.task01_objects_b.action.Calculator;
import by.guretsky.task01_objects_b.entity.Point;
import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.exception.IncorrectQuadrangleDataException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Test class for {@link Calculator}.
 */
public class CalculatorTest {
    /**
     * {@link Calculator} object.
     */
    private final Calculator calculator =
            new Calculator();

    /**
     * Data provider to square test method.
     *
     * @return Object[][]
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @DataProvider(name = "data_for_square")
    public Object[][] createCorrectData1() throws
            IncorrectQuadrangleDataException {
        final double firstExpectedSquare = 30.0;
        final double secondExpectedSquare = 8.0;
        return new Object[][]{
                {new Quadrangle(Arrays
                        .asList(new Point(2.0, 2.0),
                                new Point(4.0, -3.0),
                                new Point(-4.0, -3.0),
                                new Point(-2.0, 2.0))),
                        firstExpectedSquare},
                {new Quadrangle(Arrays
                        .asList(new Point(1.0, 4.0),
                                new Point(1.0, 0.0),
                                new Point(-1.0, 0.0),
                                new Point(-1.0, 4.0))),
                        secondExpectedSquare},
        };
    }

    /**
     * Positive test method for square calculation.
     *
     * @param quadrangle {@link Quadrangle} object
     * @param s          expected square
     */
    @Test(description = "Positive script of the square calculation",
            dataProvider = "data_for_square")
    public void testSquare(final Quadrangle quadrangle, final double s) {
        Double expected = s;
        Double actual = calculator.square(quadrangle);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Data provider to perimeter test method.
     *
     * @return Object[][]
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @DataProvider(name = "data_for_perimeter")
    public Object[][] createCorrectData2() throws
            IncorrectQuadrangleDataException {
        final double expectedPerimeter1 = 16.0;
        final double expectedPerimeter2 = 10.0;
        return new Object[][]{
                {new Quadrangle(Arrays
                        .asList(new Point(2.0, 2.0),
                                new Point(2.0, -2.0),
                                new Point(-2.0, -2.0),
                                new Point(-2.0, 2.0))),
                        expectedPerimeter1},
                {new Quadrangle(Arrays
                        .asList(new Point(1.0, 3.0),
                                new Point(1.0, 0.0),
                                new Point(-1.0, 0.0),
                                new Point(-1.0, 3.0))),
                        expectedPerimeter2},
        };
    }

    /**
     * Positive test method for perimeter calculation.
     *
     * @param quadrangle {@link Quadrangle} object
     * @param s          expected perimeter
     */
    @Test(description = "Positive script of the perimeter calculation",
            dataProvider = "data_for_perimeter")
    public void testPerimeter(final Quadrangle quadrangle, final double s) {
        Double expected = s;
        Double actual = calculator.perimeter(quadrangle);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Data provider to side calculate test method.
     *
     * @return Object[][]
     */
    @DataProvider(name = "data_for_side")
    public Object[][] createCorrectData3() {
        final double expectedSideLength1 = 6.0;
        final double expectedSideLength2 = 5.0;
        final double expectedSideLength3 = 0.0;
        return new Object[][]{
                {new Point(-2.0, 2.0),
                        new Point(4.0, 2.0),
                        expectedSideLength1},
                {new Point(-2.0, -1.0),
                        new Point(3.0, -1.0),
                        expectedSideLength2},
                {new Point(2.0, 2.0),
                        new Point(2.0, 2.0),
                        expectedSideLength3}
        };
    }

    /**
     * Positive test method for side calculate.
     *
     * @param point1 first side point
     * @param point2 second side point
     * @param len    expected side length
     */
    @Test(description = "Positive script for the side length calculator",
            dataProvider = "data_for_side")
    public void testCalculateSide(final Point point1, final Point point2,
                                  final double len) {
        Double expected = len;
        Double actual = calculator.calculateSide(point1, point2);
        Assert.assertEquals(actual, expected);
    }
}
