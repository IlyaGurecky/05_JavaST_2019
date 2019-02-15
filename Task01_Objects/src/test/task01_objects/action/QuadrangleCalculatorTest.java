package test.task01_objects.action;

import by.guretsky.task01_objects.action.QuadrangleCalculator;
import by.guretsky.task01_objects.entity.PointForQuadrangle;
import by.guretsky.task01_objects.entity.Quadrangle;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

@SuppressWarnings("CheckStyle")
public class QuadrangleCalculatorTest {
    private QuadrangleCalculator quadrangleCalculator = new QuadrangleCalculator();

    @DataProvider(name = "data_for_square")
    public Object[][] createCorrectData1() {
        PointForQuadrangle point1 = new PointForQuadrangle(2.0, 2.0);
        PointForQuadrangle point2 = new PointForQuadrangle(4.0, -3.0);
        PointForQuadrangle point3 = new PointForQuadrangle(-4.0, -3.0);
        PointForQuadrangle point4 = new PointForQuadrangle(-2.0, 2.0);

        return new Object[][]{
                {new Quadrangle(Arrays.asList(point1, point2, point3, point4)), 30.0},

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
        PointForQuadrangle point1 = new PointForQuadrangle(2.0, 2.0);
        PointForQuadrangle point2 = new PointForQuadrangle(2.0, -2.0);
        PointForQuadrangle point3 = new PointForQuadrangle(-2.0, -2.0);
        PointForQuadrangle point4 = new PointForQuadrangle(-2.0, 2.0);

        return new Object[][]{
                {new Quadrangle(Arrays.asList(point1, point2, point3, point4)), 16.0},

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
