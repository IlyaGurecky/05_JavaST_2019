package by.guretsky.task2_threads.creator;

import by.guretsky.task2_threads.entity.Train;
import by.guretsky.task2_threads.exception.IncorrectArgumentException;
import by.guretsky.task2_threads.validator.TrainValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TrainCreator {
    private static final Logger LOGGER
            = LogManager.getLogger(TrainCreator.class);

    public List<Train> createTrainsList(final List<Integer> directions) {
        List<Train> trains = new ArrayList<>();
        TrainValidator validator = new TrainValidator();
        directions.forEach(direction -> {
            if (validator.validateInfo(direction)) {
                try {
                    trains.add(new Train(direction));
                } catch (IncorrectArgumentException e) {
                    LOGGER.error("Incorrect argument to create an object");
                }
            } else {
                LOGGER.info("Direction: " + direction + " is incorrect");
            }
        });
        return trains;
    }
}
