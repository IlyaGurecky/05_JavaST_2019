package by.guretsky.task01_objects.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter class. Used to choose correct information.
 */
public class DataFilter {
    /**
     * Logger, which used to log events.
     */
    private static final Logger LOGGER = LogManager.getLogger(DataFilter.class);
    /**
     * Regular expression, which used to filter data.
     */
    private static final String DIGITS_PATTERN = "-?\\d+(\\.\\d*)?";
    /**
     * Regular expression, which used to split string.
     */
    private static final String SPLIT_PATTERN = "\\s+";

    /**
     * The method used to filter data and create list of correct information.
     *
     * @param fileData string list of the data to be filtered
     * @return filtered string list
     */
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
