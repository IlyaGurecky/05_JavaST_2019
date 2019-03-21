package by.guretsky.task03.exception;

/**
 * Signals about argument error.
 */
public class IncorrectArgumentException extends Exception {

    /**
     * Constructs a new exception.
     */
    public IncorrectArgumentException() {
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message detail message
     */
    public IncorrectArgumentException(final String message) {
        super(message);
    }
}
