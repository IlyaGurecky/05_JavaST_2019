package test.by.guretsky.task03.action;

import by.guretsky.task03.action.TextSorter;
import by.guretsky.task03.creator.TextTreeCreator;
import by.guretsky.task03.entity.Component;
import by.guretsky.task03.exception.IllegalOperationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for {@link TextSorter}.
 */
public class TextSorterTest {
    /**
     * {@link TextSorter} object.
     */
    private final TextSorter sorter = new TextSorter();
    /**
     * {@link TextTreeCreator} object.
     */
    private final TextTreeCreator creator = new TextTreeCreator();

    /**
     * Positive test for
     * {@link TextSorter#sortParagraphsBySentences(Component)}.
     *
     * @throws IllegalOperationException if operation is a leaf
     */
    @Test(description = "Positive test for text paragraphs sorter")
    public void testSortParagraphsBySentences() throws
            IllegalOperationException {
        String stringText = "    It is a long established fact that a reader"
                + " will be distracted by the readable\n"
                + "content of a page when looking at its layout. The point of "
                + "using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78\n"
                + "Ipsum is that it has a more-or-less normal distribution of "
                + "letters, as\n"
                + "opposed to using (Content here), content here', making it "
                + "look like readable English.\n    It is a (111^5|1&2<<(2|5>>"
                + "2&71))|1200 established fact that a reader will be of a\n"
                + "page when looking at its layout.\n"
                + "    Bye.";
        String expectedString = "    It is a 1274 established fact that a "
                + "reader will be of a page when looking at its layout.\n"
                + "    Bye.\n"
                + "    It is a long established fact that a reader will be "
                + "distracted by the readable content of a page when looking at"
                + " its layout. The point of using 78 Ipsum is that it has a "
                + "more-or-less normal distribution of letters, as opposed to "
                + "using (Content here), content here', making it look like "
                + "readable English.\n";

        Component text = creator.createTextTree(stringText);
        List<Component> actualSortedText =
                sorter.sortParagraphsBySentences(text);
        StringBuilder actualSortedTextString = new StringBuilder();
        actualSortedText.forEach(component ->
                actualSortedTextString.append(component.toString()));
        Assert.assertEquals(actualSortedTextString.toString(), expectedString);
    }

    /**
     * Positive test for
     * {@link TextSorter#sortSentencesByWordLength(Component)}.
     *
     * @throws IllegalOperationException if operation is a leaf
     */
    @Test(description = "Positive test for a sentences sort")
    public void testSortSentencesByWordLength() throws
            IllegalOperationException {
        String data = "    It is a (111^5|1&2<<(2|5>>2&71))|1200 established "
                + "fact that a reader will be of a\n"
                + "page when looking at its layout.";
        String expected = " a a a It is be of at its fact that will page when "
                + "reader layout. looking established 1274";
        Component text = creator.createTextTree(data);
        List<Component> actualSortedSentence =
                sorter.sortSentencesByWordLength(text);
        StringBuilder actualSortedSentenceString = new StringBuilder();
        actualSortedSentence.forEach(component ->
                actualSortedSentenceString.append(component.toString()));
        Assert.assertEquals(actualSortedSentenceString.toString(), expected);
    }

    /**
     * Positive test for
     * {@link TextSorter#sortLexemesBySymbols(Component, char)}.
     *
     * @throws IllegalOperationException if operation is a leaf
     */
    @Test(description = "Positive test for a text by lexemes sorter")
    public void testSortLexemesBySymbols() throws IllegalOperationException {
        String data = "    It is a (111^5|1&2<<(2|5>>2&71))|1200 established "
                + "fact that a reader will be of a\n" + "page when looking "
                + "at its layout.";
        String expected = " that It at established fact its layout. 1274 a a a "
                + "be is looking of page reader when will";
        Component text = creator.createTextTree(data);
        List<Component> actualSortedText =
                sorter.sortLexemesBySymbols(text, 't');
        StringBuilder actualSortedTextString = new StringBuilder();
        actualSortedText.forEach(component ->
                actualSortedTextString.append(component.toString()));
        Assert.assertEquals(actualSortedTextString.toString(), expected);
    }
}
