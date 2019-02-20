package by.guretsky.task01_objects.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class include method, which parse string numbers into double numbers.
 */
public class DataParser {
    /**
     * Regular expression for split string.
     */
    private static final String SPLIT_PATTERN = "\\s+";

    /**
     * Method parse list of the string numbers into double list and add this
     * list in Map.
     *
     * @param filteredList list of the string numbers, which we want to parse
     *                     to double
     * @return Map, which include the lists of the double numbers.
     */
    public Map<Integer, List<Double>> parseDouble(final List<String>
                                                          filteredList) {
        Map<Integer, List<Double>> parsedDataMap = new HashMap<>();
        List<Double> numbers;
        int lineCounter = 0;
        for (String line : filteredList) {
            numbers = new ArrayList<>();
            for (String stringNumber : line.split(SPLIT_PATTERN)) {
                numbers.add(Double.parseDouble(stringNumber));
            }
            parsedDataMap.put(lineCounter++, numbers);
        }
        return parsedDataMap;
    }
}
