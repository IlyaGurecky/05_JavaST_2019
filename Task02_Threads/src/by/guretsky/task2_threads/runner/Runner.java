package by.guretsky.task2_threads.runner;

import by.guretsky.task2_threads.exception.FileDoesNotExistException;
import by.guretsky.task2_threads.parser.DataParser;
import by.guretsky.task2_threads.reader.FileDataReader;
import by.guretsky.task2_threads.validator.DataFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    private static final String FILE_PATH = "data" + File.separator
            + "info.txt";

    public static void main(String[] args) {
        FileDataReader reader;
        try {
            reader = new FileDataReader(FILE_PATH);
            List<String> fileDataList = reader.readStringList();
            System.out.println(fileDataList);
            DataFilter filter = new DataFilter();
            List<String> filteredDataList = filter.filterFileData(fileDataList);
            System.out.println(filteredDataList);
            DataParser parser = new DataParser();
            List<Integer> trainInfo
                    = parser.parseInt(filteredDataList);
            System.out.println(trainInfo);
        } catch (FileDoesNotExistException e) {
            LOGGER.error("File doesn't not exist");
        }
    }
}
