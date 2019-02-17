package by.guretsky.task01_objects.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParser {
    private static final String SPLIT_PATTERN = "\\s+";

    public Map<Integer, List<Double>> parseDouble(final List<String> filteredList) {
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
