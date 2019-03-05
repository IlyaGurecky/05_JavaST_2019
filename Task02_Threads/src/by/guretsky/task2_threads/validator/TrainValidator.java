package by.guretsky.task2_threads.validator;

/**
 * Validation class. Check if data is correct to create an object.
 */
public class TrainValidator {

    public boolean validateInfo(final Integer trainInfo) {
        final int firstDirection = 1;
        final int secondDirection = 2;

        return trainInfo.equals(firstDirection)
                || trainInfo.equals(secondDirection);
    }
}
