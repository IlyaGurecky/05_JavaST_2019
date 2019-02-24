package test.task01_objects_b.action;

import by.guretsky.task01_objects_b.action.ShapeExplorer;
import by.guretsky.task01_objects_b.entity.Point;
import by.guretsky.task01_objects_b.entity.Quadrangle;
import by.guretsky.task01_objects_b.exception.IncorrectQuadrangleDataException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Test class for {@link ShapeExplorer}.
 */
public class ShapeExplorerTest {
    /**
     * Quadrangle object.
     */
    private Quadrangle quadrangle;

    /**
     * Positive test method for {@link ShapeExplorer#isSquare()}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Positive script for the square test")
    public void testIsSquare1() throws IncorrectQuadrangleDataException {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-1.0, -1.0),
                        new Point(-1.0, 3.0),
                        new Point(3.0, 3.0),
                        new Point(3.0, -1.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isSquare = shapeExplorer.isSquare();
        Assert.assertTrue(isSquare);
    }

    /**
     * Negative test method for {@link ShapeExplorer#isSquare()}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Negative script for the square test")
    public void testIsSquare2() throws IncorrectQuadrangleDataException {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-2.0, 2.0),
                        new Point(2.0, 3.0),
                        new Point(4.0, -1.0),
                        new Point(1.0, -3.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isSquare = shapeExplorer.isSquare();
        Assert.assertFalse(isSquare);
    }

    /**
     * Positive test method for {@link ShapeExplorer#isRhombus()}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Positive script for the rhombus test")
    public void testIsRhombus1() throws IncorrectQuadrangleDataException {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(0.0, 3.0),
                        new Point(2.0, 0.0),
                        new Point(0.0, -3.0),
                        new Point(-2.0, 0.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isRhombus = shapeExplorer.isRhombus();
        Assert.assertTrue(isRhombus);
    }

    /**
     * Negative test method for {@link ShapeExplorer#isRhombus()}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Negative script for the rhombus test")
    public void testIsRhombus2() throws IncorrectQuadrangleDataException {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(1.0, 3.0),
                        new Point(2.0, 0.0),
                        new Point(0.0, -3.0),
                        new Point(-2.0, 0.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isRhombus = shapeExplorer.isRhombus();
        Assert.assertFalse(isRhombus);
    }

    /**
     * Positive test method for {@link ShapeExplorer#isTrapezium()}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Positive script for the trapezium test")
    public void testIsTrapezium1() throws IncorrectQuadrangleDataException {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-3.0, 2.0),
                        new Point(2.0, 2.0),
                        new Point(2.0, -6.0),
                        new Point(-3.0, -7.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isTrapezium = shapeExplorer.isTrapezium();
        Assert.assertTrue(isTrapezium);
    }

    /**
     * Negative test method for {@link ShapeExplorer#isTrapezium()}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Negative script for the trapezium test")
    public void testIsTrapezium2() throws IncorrectQuadrangleDataException {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-1.0, 2.0),
                        new Point(2.0, 3.0),
                        new Point(3.0, -2.0),
                        new Point(-4.0, -4.0)));
        ShapeExplorer shapeExplorer = new ShapeExplorer(quadrangle);
        boolean isTrapezium = shapeExplorer.isTrapezium();
        Assert.assertFalse(isTrapezium);
    }

    /**
     * Negative test method for {@link ShapeExplorer#isConvex()}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Negative script for the quadrangle convex test")
    public void testIsConvex1() throws IncorrectQuadrangleDataException {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-2.0, 1.0),
                        new Point(1.0, 3.0),
                        new Point(3.0, 1.0),
                        new Point(1.0, 2.0)));
        ShapeExplorer quadrangleType = new ShapeExplorer(quadrangle);
        boolean isConvex = quadrangleType.isConvex();
        Assert.assertFalse(isConvex);
    }

    /**
     * Positive test method for {@link ShapeExplorer#isConvex()}.
     *
     * @throws IncorrectQuadrangleDataException if amount of points are not
     *                                            equals to 4
     */
    @Test(description = "Positive script for the quadrangle convex test")
    public void testIsConvex2() throws IncorrectQuadrangleDataException {
        quadrangle = new Quadrangle(Arrays
                .asList(new Point(-2.0, 2.0),
                        new Point(4.0, 5.0),
                        new Point(3.0, -3.0),
                        new Point(-3.0, -4.0)));
        ShapeExplorer quadrangleType = new ShapeExplorer(quadrangle);
        boolean isConvex = quadrangleType.isConvex();
        Assert.assertTrue(isConvex);
    }
}
