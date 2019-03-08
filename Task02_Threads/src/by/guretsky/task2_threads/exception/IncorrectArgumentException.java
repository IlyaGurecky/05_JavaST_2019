package by.guretsky.task2_threads.exception;

/**
 * Signals that argument is incorrect.
 */
public class IncorrectArgumentException extends Exception {
    /**
     * Constructs a new {@link IncorrectArgumentException}.
     */
    public IncorrectArgumentException() {
    }

    /**
     * Constructs a new {@link IncorrectArgumentException} with the specified
     * detail message.
     *
     * @param message detail message
     */
    public IncorrectArgumentException(final String message) {
        super(message);
    }
}
