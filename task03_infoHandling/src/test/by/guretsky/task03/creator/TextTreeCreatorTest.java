package test.by.guretsky.task03.creator;

import by.guretsky.task03.creator.TextTreeCreator;
import by.guretsky.task03.entity.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for {@link TextTreeCreator}.
 */
public class TextTreeCreatorTest {
    /**
     * Test method for {@link TextTreeCreator#createTextTree(String)}.
     */
    @Test(description = "Positive test method for text tree creator")
    public void testCreateTextTree() {
        String string = "    It is a (111^5|1&2<<(2|5>>2&71))|1200 "
                + "established fact that a reader will be of a"
                + "page when looking at its layout.\n"
                + "    Bye.\n";
        String expectedString = "    It is a 1274 "
                + "established fact that a reader will be of a"
                + "page when looking at its layout.\n"
                + "    Bye.\n";
        TextTreeCreator creator = new TextTreeCreator();
        Component text = creator.createTextTree(string);
        Assert.assertEquals(text.toString(), expectedString);
    }
}
