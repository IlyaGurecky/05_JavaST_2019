package by.guretsky.task2_threads.validator;

/**
 * Validation class. Check if data is correct to create an object.
 */
public class TrainValidator {
    /**
     * The method checks if train direction is correct to create an
     * {@link by.guretsky.task2_threads.entity.Train}.
     *
     * @param trainInfo train direction
     * @return true if direction is correct
     */
    public boolean validateInfo(final Integer trainInfo) {
        final int firstDirection = 1;
        final int secondDirection = 2;

        return trainInfo.equals(firstDirection)
                || trainInfo.equals(secondDirection);
    }
}
