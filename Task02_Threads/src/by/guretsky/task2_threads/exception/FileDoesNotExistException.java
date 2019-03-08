package by.guretsky.task2_threads.exception;

/**
 * Signals that file doesn't exist.
 */
public class FileDoesNotExistException extends Exception {
    /**
     * Constructs a new {@link FileDoesNotExistException}.
     */
    public FileDoesNotExistException() {
        super();
    }

    /**
     * Constructs a new {@link FileDoesNotExistException} with the specified
     * detail message.
     *
     * @param message detail message
     */
    public FileDoesNotExistException(final String message) {
        super(message);
    }
}
