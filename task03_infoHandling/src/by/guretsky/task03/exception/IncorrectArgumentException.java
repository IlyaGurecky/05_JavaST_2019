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
     * Constructs a new exception with special message.
     */
    public IncorrectArgumentException(String message) {
        super(message);
    }
}
