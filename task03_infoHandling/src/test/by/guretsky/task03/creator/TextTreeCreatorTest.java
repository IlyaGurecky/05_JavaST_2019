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
        String string = "    It has survived - not only (five) centuries, "
                + "but also the leap into 13<<2\nelectronic typesetting, "
                + "remaining 30>>>3 essentially ~6&9|(3&4), unchanged... "
                + "It was\n"
                + "popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with "
                + "the release of Letraset sheets\n"
                + "containing Lorem Ipsum passages, and more recently with "
                + "desktop publishing software like\n"
                + "Aldus PageMaker including versions of Lorem Ipsum.\n"
                + "    It is a long established fact that a reader will be "
                + "distracted by the readable\n"
                + "content of a page when looking at its layout. "
                + "The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78\n"
                + "Ipsum is that it has a more-or-less normal distribution of "
                + "letters, as\nopposed to using (Content here), content here',"
                + " making it look like readable English.\n"
                + "    It is a (111^5|1&2<<(2|5>>2&71))|1200 established fact "
                + "that a reader will be of a\npage when looking at its "
                + "layout.\n    Bye.\n";
        String expectedString = "    It has survived - not only (five) "
                + "centuries, but also the leap into 52 electronic typesetting,"
                + " remaining 3 essentially 9, unchanged... It was popularised"
                + " in the 5 with the release of Letraset sheets containing "
                + "Lorem Ipsum passages, and more recently with desktop "
                + "publishing software like Aldus PageMaker including versions"
                + " of Lorem Ipsum.\n    It is a long established fact that a "
                + "reader will be distracted by the readable content of a page "
                + "when looking at its layout. The point of using 78 Ipsum is "
                + "that it has a more-or-less normal distribution of letters, "
                + "as opposed to using (Content here), content here', making it"
                + " look like readable English.\n    It is a 1274 established "
                + "fact that a reader will be of a page when looking at its "
                + "layout.\n    Bye.\n";
        TextTreeCreator creator = new TextTreeCreator();
        Component text = creator.createTextTree(string);
        Assert.assertEquals(text.toString(), expectedString);
    }
}
