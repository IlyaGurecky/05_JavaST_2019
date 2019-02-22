package by.guretsky.task01_objects_b.exception;

/**
 * Signals that points can't create a quadrangle.
 */
public class IncorrectQuadranglePointsException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message detail message
     */
    public IncorrectQuadranglePointsException(final String message) {
        super(message);
    }
}
