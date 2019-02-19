package test.task01_objects.action;

import by.guretsky.task01_objects.action.ShapeExplorer;
import by.guretsky.task01_objects.entity.Point;
import by.guretsky.task01_objects.entity.Quadrangle;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

@SuppressWarnings("CheckStyle")
public class ShapeExplorerTest {
    private Quadrangle quadrangle;

    @Test(description = "Positive script for the square test")
    public void testIsSquare1() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-1.0, -1.0),
                        new Point(-1.0, 3.0),
                        new Point(3.0, 3.0),
                        new Point(3.0, -1.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isSquare = shapeExplorer.isSquare();
        Assert.assertTrue(isSquare);
    }

    @Test(description = "Negative script for the square test")
    public void testIsSquare2() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-2.0, 2.0),
                        new Point(2.0, 3.0),
                        new Point(4.0, -1.0),
                        new Point(1.0, -3.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isSquare = shapeExplorer.isSquare();
        Assert.assertFalse(isSquare);
    }

    @Test(description = "Positive script for the rhombus test")
    public void testIsRhombus1() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(0.0, 3.0),
                        new Point(2.0, 0.0),
                        new Point(0.0, -3.0),
                        new Point(-2.0, 0.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isRhombus = shapeExplorer.isRhombus();
        Assert.assertTrue(isRhombus);
    }

    @Test(description = "Negative script for the rhombus test")
    public void testIsRhombus2() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(1.0, 3.0),
                        new Point(2.0, 0.0),
                        new Point(0.0, -3.0),
                        new Point(-2.0, 0.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isRhombus = shapeExplorer.isRhombus();
        Assert.assertFalse(isRhombus);
    }

    @Test(description = "Positive script for the trapezium test")
    public void testIsTrapezium1() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-3.0, 2.0),
                        new Point(2.0, 2.0),
                        new Point(2.0, -6.0),
                        new Point(-3.0, -7.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isTrapezium = shapeExplorer.isTrapezium();
        Assert.assertTrue(isTrapezium);
    }

    @Test(description = "Negative script for the trapezium test")
    public void testIsTrapezium2() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-1.0, 2.0),
                        new Point(2.0, 3.0),
                        new Point(3.0, -2.0),
                        new Point(-4.0, -4.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isTrapezium = shapeExplorer.isTrapezium();
        Assert.assertFalse(isTrapezium);
    }

    /*@Test(description = "Positive script for the quadrangle convex test")
    public void testIsConvex1() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-2.0, 1.0),
                        new Point(1.0, 3.0),
                        new Point(3.0, 1.0),
                        new Point(1.0, 2.0)));
        ShapeExplorer quadrangleType = new ShapeExplorer(quadrangle);
        boolean isConvex = quadrangleType.isConvex();
        Assert.assertTrue(isConvex);
    }*/

    /*@Test(description = "Negative script for the quadrangle convex test")
    public void testIsConvex2() {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-2.0, 2.0),
                        new Point(4.0, 5.0),
                        new Point(3.0, -3.0),
                        new Point(-3.0, -4.0)));
        ShapeExplorer quadrangleType = new ShapeExplorer(quadrangle);
        boolean isConvex = quadrangleType.isConvex();
        Assert.assertFalse(isConvex);
    }*/

}
