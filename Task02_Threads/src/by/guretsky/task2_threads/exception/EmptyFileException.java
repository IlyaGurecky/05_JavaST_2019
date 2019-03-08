package by.guretsky.task2_threads.exception;

/**
 * Signals that file is empty.
 */
public class EmptyFileException extends Exception {

    /**
     * Constructs a new {@link EmptyFileException}.
     */
    public EmptyFileException() {
        super();
    }

    /**
     * Constructs a new {@link EmptyFileException} with the specified
     * detail message.
     *
     * @param message detail message
     */
    public EmptyFileException(final String message) {
        super(message);
    }
}
