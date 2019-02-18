package test.task01_objects.action;

import by.guretsky.task01_objects.action.QuadrangleType;
import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

@SuppressWarnings("CheckStyle")
public class QuadrangleTypeTest {
    private Quadrangle quadrangle;

    @Test(description = "Positive script for the square test")
    public void testIsSquare1() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-1.0, -1.0),
                        new Point(-1.0, 3.0),
                        new Point(3.0, 3.0),
                        new Point(3.0, -1.0)));
        QuadrangleType quadrangleType = new QuadrangleType(quadrangle);
        boolean isSquare = quadrangleType.isSquare();
        Assert.assertTrue(isSquare);
    }

    @Test(description = "Negative script for the square test") //???
    public void testIsSquare2() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-2.0, 2.0),
                        new Point(2.0, 3.0),
                        new Point(4.0, -1.0),
                        new Point(1.0, -3.0)));
        QuadrangleType quadrangleType = new QuadrangleType(quadrangle);
        boolean isSquare = quadrangleType.isSquare();
        Assert.assertFalse(isSquare);
    }

    @Test(description = "Positive script for the rhombus test")
    public void testIsRhombus1() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(0.0, 3.0),
                        new Point(2.0, 0.0),
                        new Point(0.0, -3.0),
                        new Point(-2.0, 0.0)));
        QuadrangleType quadrangleType = new QuadrangleType(quadrangle);
        boolean isRhombus = quadrangleType.isRhombus();
        Assert.assertTrue(isRhombus);
    }

    @Test(description = "Negative script for the rhombus test")
    public void testIsRhombus2() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(1.0, 3.0),
                        new Point(2.0, 0.0),
                        new Point(0.0, -3.0),
                        new Point(-2.0, 0.0)));
        QuadrangleType quadrangleType = new QuadrangleType(quadrangle);
        boolean isRhombus = quadrangleType.isRhombus();
        Assert.assertFalse(isRhombus);
    }

}
