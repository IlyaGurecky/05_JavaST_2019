package test.task01_objects.parser;

import by.guretsky.task01_objects.parser.DataParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

@SuppressWarnings("CheckStyle")
public class DataParserTest {
    private Map<Integer, List<Double>> expected;
    private List<String> stringList;

    @BeforeClass
    public void initialization() {
        stringList = new ArrayList<>(Arrays.asList("1 2 3 4", "2 4 5 6"));
        List<Double> doubles1 = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0));
        List<Double> doubles2 = new ArrayList<>(Arrays.asList(2.0, 4.0, 5.0, 6.0));

        expected = new HashMap<>();
        expected.put(0, doubles1);
        expected.put(1, doubles2);
    }

    @Test
    public void testParseDouble() {
        DataParser dataParser = new DataParser();
        Map<Integer, List<Double>> actualParsedDataMap = dataParser
                .parseDouble(stringList);

        Assert.assertEquals(actualParsedDataMap, expected);
    }
}