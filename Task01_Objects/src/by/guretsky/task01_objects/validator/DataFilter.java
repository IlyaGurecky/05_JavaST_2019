package by.guretsky.task01_objects.validator;

import by.guretsky.task01_objects.exception.FileDoesNotExistException;
import by.guretsky.task01_objects.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
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
            /*if (str.equals("")) {
                LOGGER.info("Line is empty");
            }*/
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
            }
        }

        return filteredData;
    }

    public static void main(String[] args) throws FileDoesNotExistException {
        DataReader reader = new DataReader("data" + File.separator
                + "info.txt");
        DataFilter filter = new DataFilter();
        List<String> list = new ArrayList<>();
        list = reader.readStringList();
        List<String> flist = filter.filterFileData(list);
        System.out.println(flist);
    }
}
