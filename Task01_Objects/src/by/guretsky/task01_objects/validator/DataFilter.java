package by.guretsky.task01_objects.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DataFilter {
    private static final Logger LOGGER = LogManager.getLogger(DataFilter.class);
    private static final String DIGITS_PATTERN = "-?\\d+(\\.\\d*)?";
    private static final String SPLIT_PATTERN = "\\s+";

    public List<String> filterFileData(final List<String> fileData) {
        List<String> filteredData = new ArrayList<>();
        final int amountOfNumbersInLine = 8;
        boolean isCorrect;
        for (String str : fileData) {
            isCorrect = true;
            String[] splitString = str.split(SPLIT_PATTERN);
            for (String elem : splitString) {
                if (!elem.matches(DIGITS_PATTERN)
                        || splitString.length != amountOfNumbersInLine) {
                    isCorrect = false;
                }
            }
            if (isCorrect) {
                filteredData.add(str);
            } else if (str.equals("")) {
                LOGGER.info("Line is empty");
            } else {
                LOGGER.info("Line: " + str + " is incorrect");
            }
        }

        return filteredData;
    }
}
