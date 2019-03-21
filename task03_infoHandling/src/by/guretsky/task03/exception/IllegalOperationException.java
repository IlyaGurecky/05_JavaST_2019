package by.guretsky.task03.exception;

/**
 * Signals about operation error.
 */
public class IllegalOperationException extends Exception {
    /**
     * Constructs a new exception.
     */
    public IllegalOperationException() {
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message detail message
     */
    public IllegalOperationException(final String message) {
        super(message);
    }
}
