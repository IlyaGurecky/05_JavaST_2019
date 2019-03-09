package by.guretsky.task2_threads.runner;

import by.guretsky.task2_threads.common_resource.RailwaySingleton;
import by.guretsky.task2_threads.creator.TrainCreator;
import by.guretsky.task2_threads.entity.Train;
import by.guretsky.task2_threads.exception.FileDoesNotExistException;
import by.guretsky.task2_threads.parser.DataParser;
import by.guretsky.task2_threads.reader.FileDataReader;
import by.guretsky.task2_threads.validator.DataFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runner class.
 */
public final class Runner {
    /**
     * Logger, used for log events.
     */
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    /**
     * Info file path.
     */
    private static final String FILE_PATH = "data" + File.separator
            + "info.txt";

    /**
     * Private constructor without parameters.
     */
    private Runner() {
    }

    /**
     * Main method, which run application.
     *
     * @param args arguments from command line.
     */
    public static void main(final String[] args) {
        FileDataReader reader;
        List<Integer> trainInfo = new ArrayList<>();
        try {
            reader = new FileDataReader(FILE_PATH);
            List<String> fileDataList = reader.readStringList();
            DataFilter filter = new DataFilter();
            List<String> filteredDataList = filter.filterFileData(fileDataList);
            DataParser parser = new DataParser();
            trainInfo.addAll(parser.parseInt(filteredDataList));
            System.out.println(trainInfo);
        } catch (FileDoesNotExistException e) {
            LOGGER.error("File doesn't not exist");
        }

        TrainCreator creator = new TrainCreator();
        List<Train> trains = creator.createTrainsList(trainInfo);
        int railwayCapacity = RailwaySingleton.getRailwayCapacity();
        ExecutorService service = Executors.newFixedThreadPool(railwayCapacity);
        try {
            service.invokeAll(trains);
        } catch (InterruptedException e) {
            LOGGER.error("Invoke error");
        } finally {
            service.shutdown();
        }
    }
}
