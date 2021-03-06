package by.guretsky.task01_objects_b.exception;

/**
 * Signals that points can't create a quadrangle.
 */
public class IncorrectQuadrangleDataException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message detail message
     */
    public IncorrectQuadrangleDataException(final String message) {
        super(message);
    }
}
