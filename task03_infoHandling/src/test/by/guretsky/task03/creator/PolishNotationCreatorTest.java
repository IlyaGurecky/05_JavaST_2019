package test.by.guretsky.task03.creator;

import by.guretsky.task03.creator.PolishNotationCreator;
import by.guretsky.task03.exception.IncorrectArgumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for {@link PolishNotationCreator}.
 *
 * @author ilyaguretsky
 */
public class PolishNotationCreatorTest {
    /**
     * {@link PolishNotationCreator} object.
     */
    private final PolishNotationCreator creator = new PolishNotationCreator();

    /**
     * Data provider for the test.
     *
     * @return Object[][]
     */
    @DataProvider(name = "data_for_polish_notation_creator")
    public Object[][] createCorrectData() {
        final List<String> expectedNotation1 =
                new ArrayList<>(Arrays.asList("30", "3", ">>>"));
        final List<String> expectedNotation2 =
                new ArrayList<>(Arrays.asList("13", "2", "<<"));
        final List<String> expectedNotation3 =
                new ArrayList<>(Arrays.asList("5", "1", "2", "&", "3", "4",
                        "25", "5", "^", "6", "47", "&", "|", "&", "3", "|", "|",
                        "2", "|", "&", "1", "|", "|"));
        final List<String> expectedNotation4 =
                new ArrayList<>(Arrays.asList("71", "~", "2", "3", "&", "3",
                        "2", "1", "2", ">>", "2", "|", "&", "2", "&", "|", "|",
                        "10", "2", "&", "|", "&", "78", "|"));
        final List<String> expectedNotation5 =
                new ArrayList<>(Arrays.asList("111", "5", "^", "1", "2", "2",
                        "5", "2", ">>", "71", "&", "|", "<<", "&", "|",
                        "1200", "|"));
        final List<String> expectedNotation6 =
                new ArrayList<>(Arrays.asList("4", "5", "~", "|"));

        return new Object[][]{
                {"30>>>3", expectedNotation1},
                {"13<<2", expectedNotation2},
                {"5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)", expectedNotation3},
                {"(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", expectedNotation4},
                {"(111^5|1&2<<(2|5>>2&71))|1200", expectedNotation5},
                {"4|~5", expectedNotation6}
        };
    }

    /**
     * Data provider for the test.
     *
     * @return Object[][]
     */
    @DataProvider(name = "negative_data_for_polish_notation_creator")
    public Object[][] createIncorrectData() {
        return new Object[][]{
                {null, Arrays.asList("", "")},
                {"", Arrays.asList("", "")},
        };
    }

    /**
     * Positive test for
     * {@link PolishNotationCreator#createPolishNotation(String)}.
     *
     * @param expression       expression you need to parse to polish notation
     * @param expectedNotation expected polish notation
     * @throws IncorrectArgumentException if expression is incorrect
     */
    @Test(description = "Positive exception for polish notation creator",
            dataProvider = "data_for_polish_notation_creator")
    public void testCreatePolishNotation(final String expression,
                                         final List<String> expectedNotation)
            throws IncorrectArgumentException {
        List<String> actualNotation = creator.createPolishNotation(expression);
        Assert.assertEquals(actualNotation, expectedNotation);
    }

    /**
     * Negative test for
     * {@link PolishNotationCreator#createPolishNotation(String)}.
     *
     * @param expression       expression you need to parse to polish notation
     * @param expectedNotation expected polish notation
     * @throws IncorrectArgumentException if expression is incorrect
     */
    @Test(description = "Negative exception for polish notation creator",
            dataProvider = "negative_data_for_polish_notation_creator",
            expectedExceptions = {IncorrectArgumentException.class})
    public void testCreatePolishNotation2(final String expression,
                                          final List<String> expectedNotation)
            throws IncorrectArgumentException {
        List<String> actualNotation = creator.createPolishNotation(expression);
        Assert.assertEquals(actualNotation, expectedNotation);
    }
}
