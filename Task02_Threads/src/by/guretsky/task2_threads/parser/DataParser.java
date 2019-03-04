package by.guretsky.task2_threads.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Class include method, which parse string numbers into int numbers.
 */
public class DataParser {
    /**
     * The method parse list of the string numbers into int and add in Map.
     *
     * @param filteredList list of the string numbers, which we want to parse
     *                     to int
     * @return Map, which include the int numbers.
     */
    public List<Integer> parseInt(final List<String>
                                          filteredList) {
        List<Integer> parsedDataMap = new ArrayList<>();
        int lineCounter = 0;
        for (String number : filteredList) {
            parsedDataMap.add(lineCounter++, Integer.parseInt(number));
        }
        return parsedDataMap;
    }
}

