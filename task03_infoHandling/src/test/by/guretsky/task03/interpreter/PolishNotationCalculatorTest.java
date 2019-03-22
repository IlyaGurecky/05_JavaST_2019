package test.by.guretsky.task03.interpreter;

import by.guretsky.task03.creator.PolishNotationCreator;
import by.guretsky.task03.exception.IncorrectArgumentException;
import by.guretsky.task03.interpreter.PolishNotationCalculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for {@link PolishNotationCalculator}.
 */
public class PolishNotationCalculatorTest {
    /**
     * {@link PolishNotationCreator} object.
     */
    private final PolishNotationCreator creator = new PolishNotationCreator();
    /**
     * {@link PolishNotationCalculator} object.
     */
    private PolishNotationCalculator calculator;

    /**
     * Provides correct data to a test method.
     *
     * @return Object[][]
     */
    @DataProvider(name = "positive_data_for_polish_notation_calculator")
    public Object[][] createCorrectData() {
        final int expectedValue1 = 5;
        final int expectedValue2 = 78;
        final int expectedValue3 = 1274;
        final int expectedValue4 = 3;
        final int expectedValue5 = 52;
        final int expectedValue6 = -51;
        final int expectedValue7 = 9;
        return new Object[][]{
                {"5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)", expectedValue1},
                {"(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", expectedValue2},
                {"(111^5|1&2<<(2|5>>2&71))|1200", expectedValue3},
                {"30>>3", expectedValue4},
                {"13<<2", expectedValue5},
                {"~50", expectedValue6},
                {"~6&9|(3&4)", expectedValue7}
        };
    }

    /**
     * Provides incorrect data to a test method.
     *
     * @return Object[][]
     */
    @DataProvider(name = "negative_data_for_polish_notation_calculator")
    public Object[][] createIncorrectData() {
        final int expectedValue1 = 1;
        final int expectedValue2 = 1;
        List<String> polishNotation1 = Arrays.asList("2", "<<", "3", "#");
        List<String> polishNotation2 = Arrays.asList("~", "4", "|", "5@");
        return new Object[][]{
                {polishNotation1, expectedValue1},
                {polishNotation2, expectedValue2},
        };
    }

    /**
     * Positive test for a {@link PolishNotationCalculator#calculate(List)}.
     *
     * @param expression    expression you need to calculate
     * @param expectedValue expected value
     * @throws IncorrectArgumentException if element in polish notation is
     *                                    incorrect
     */
    @Test(description = "Positive test for the polish notation calculate",
            dataProvider = "positive_data_for_polish_notation_calculator")
    public void testCalculate(final String expression,
                              final Integer expectedValue) throws
            IncorrectArgumentException {
        List<String> polishNotation = creator.createPolishNotation(expression);
        calculator = new PolishNotationCalculator();
        Integer actualValue = calculator.calculate(polishNotation);
        Assert.assertEquals(actualValue, expectedValue);
    }

    /**
     * Negative test for a {@link PolishNotationCalculator#calculate(List)}.
     *
     * @param polishNotation polish notation you need to calculate
     * @param expectedValue  expected value
     * @throws IncorrectArgumentException if element in polish notation is
     *                                    incorrect
     */
    @Test(description = "Negative test for the polish notation calculate",
            dataProvider = "negative_data_for_polish_notation_calculator",
            expectedExceptions = {IncorrectArgumentException.class})
    public void testCalculate2(final List<String> polishNotation,
                               final Integer expectedValue) throws
            IncorrectArgumentException {
        Integer actualValue = calculator.calculate(polishNotation);
        Assert.assertEquals(actualValue, expectedValue);
    }
}
