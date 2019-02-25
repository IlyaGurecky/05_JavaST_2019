package by.guretsky.task01_objects_b.exception;

/**
 * Signals, that we can't send this argument in the function.
 */
public class IncorrectArgumentException extends Exception {
    /**
     * Constructor without parameters.
     */
    public IncorrectArgumentException() {
    }

    /**
     * Constructor with special message.
     *
     * @param message special message
     */
    public IncorrectArgumentException(final String message) {
        super(message);
    }
}
