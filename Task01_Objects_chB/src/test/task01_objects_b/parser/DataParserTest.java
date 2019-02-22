package test.task01_objects_b.parser;

import by.guretsky.task01_objects_b.parser.DataParser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Test class for {@link DataParser}.
 */
public class DataParserTest {
    /**
     * Expected map.
     */
    private Map<Integer, List<Double>> expected;
    /**
     * List of the string numbers.
     */
    private List<String> stringList;

    /**
     * Initialization method.
     */
    @BeforeTest
    public void initialization() {
        stringList = new ArrayList<>(Arrays.asList("1 2 3 4", "2 4 5 6"));
        List<Double> doubles1 = new ArrayList<>(Arrays.asList(1.0, 2.0,
                3.0, 4.0));
        List<Double> doubles2 = new ArrayList<>(Arrays.asList(2.0, 4.0,
                5.0, 6.0));
        expected = new HashMap<>();
        expected.put(0, doubles1);
        expected.put(1, doubles2);
    }

    /**
     * Test method for {@link DataParser#parseDouble(List)}.
     */
    @Test(description = "Positive script for the data parser")
    public void testParseDouble() {
        DataParser dataParser = new DataParser();
        Map<Integer, List<Double>> actualParsedDataMap = dataParser
                .parseDouble(stringList);

        Assert.assertEquals(actualParsedDataMap, expected);
    }
}
