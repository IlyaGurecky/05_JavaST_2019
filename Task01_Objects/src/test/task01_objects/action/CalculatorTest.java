package test.task01_objects.action;

import by.guretsky.task01_objects.action.Calculator;
import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

@SuppressWarnings("CheckStyle")
public class CalculatorTest {
    private final Calculator calculator =
            new Calculator();

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
    public void testSquare(final Quadrangle quadrangle, final double s) {
        Double expected = s;
        Double actual = calculator.square(quadrangle);
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
    public void testPerimeter(final Quadrangle quadrangle, final double s) {
        Double expected = s;
        Double actual = calculator.perimeter(quadrangle);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "data_for_side")
    public Object[][] createCorrectData3() {
        return new Object[][]{
                {new Point(-2.0, 2.0),
                        new Point(4.0, 2.0), 6.0},
                {new Point(-2.0, -1.0),
                        new Point(3.0, -1.0), 5.0},
                {new Point(2.0, 2.0),
                        new Point(2.0, 2.0), 0}
        };
    }

    @Test(description = "Positive script for the side length calculator",
            dataProvider = "data_for_side")
    public void testCalculateSide(final Point point1, final Point point2,
                                  final double len) {
        Double expected = len;
        Double actual = calculator.calculateSide(point1, point2);
        Assert.assertEquals(actual, expected);
    }
}
