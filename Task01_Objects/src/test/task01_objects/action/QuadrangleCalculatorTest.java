package test.task01_objects.action;

import by.guretsky.task01_objects.action.QuadrangleCalculator;
import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

@SuppressWarnings("CheckStyle")
public class QuadrangleCalculatorTest {
    private final QuadrangleCalculator quadrangleCalculator =
            new QuadrangleCalculator();

    @DataProvider(name = "data_for_square")
    public Object[][] createCorrectData1() {
        return new Object[][]{
                {new Quadrangle(Arrays
                        .asList(new Point(2.0, 2.0),
                                new Point(4.0, -3.0),
                                new Point(-4.0, -3.0),
                                new Point(-2.0, 2.0))), 30.0},
                {new Quadrangle(Arrays
                        .asList(new Point(1.0, 4.0),
                                new Point(1.0, 0.0),
                                new Point(-1.0, 0.0),
                                new Point(-1.0, 4.0))), 8.0},
        };
    }

    @Test(description = "Positive script of the square calculation",
            dataProvider = "data_for_square")
    public void testSquare(Quadrangle quadrangle, double s) {
        Double expected = s;
        Double actual = quadrangleCalculator.square(quadrangle);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "data_for_perimeter")
    public Object[][] createCorrectData2() {
        return new Object[][]{
                {new Quadrangle(Arrays
                        .asList(new Point(2.0, 2.0),
                                new Point(2.0, -2.0),
                                new Point(-2.0, -2.0),
                                new Point(-2.0, 2.0))), 16.0},
                {new Quadrangle(Arrays
                        .asList(new Point(1.0, 3.0),
                                new Point(1.0, 0.0),
                                new Point(-1.0, 0.0),
                                new Point(-1.0, 3.0))), 10.0},
        };
    }

    @Test(description = "Positive script of the perimeter calculation",
            dataProvider = "data_for_perimeter")
    public void testPerimeter(Quadrangle quadrangle, double s) {
        Double expected = s;
        Double actual = quadrangleCalculator.perimeter(quadrangle);
        Assert.assertEquals(actual, expected);
    }
}
