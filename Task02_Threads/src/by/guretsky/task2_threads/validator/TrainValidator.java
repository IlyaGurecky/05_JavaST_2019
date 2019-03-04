package by.guretsky.task2_threads.validator;

/**
 * Validation class. Check if data is correct to create an object.
 */
public class TrainValidator {

    public boolean validateInfo(Integer trainInfo) {
        final int firstDirection = -1;
        final int secondDirection = 1;

        return trainInfo.equals(firstDirection)
                || trainInfo.equals(secondDirection);
    }
}
