package by.guretsky.task2_threads.creator;

import by.guretsky.task2_threads.entity.Train;
import by.guretsky.task2_threads.validator.TrainValidator;

import java.util.ArrayList;
import java.util.List;

public class TrainCreator {
    public List<Train> createTrainsList(final List<Integer> directions) {
        List<Train> trains = new ArrayList<>();
        TrainValidator validator = new TrainValidator();
        directions.forEach(direction -> {
            if (validator.validateInfo(direction)) {
                trains.add(new Train(direction));
            }
        });
        return trains;
    }
}
