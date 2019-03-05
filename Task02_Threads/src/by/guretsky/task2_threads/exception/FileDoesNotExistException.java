package by.guretsky.task2_threads.exception;

/**
 * Signals that file doesn't exist.
 */
public class FileDoesNotExistException extends Exception {
    /**
     * Constructs a new by.guretsky.task2_threads.exception.
     */
    public FileDoesNotExistException() {
        super();
    }

    /**
     * Constructs a new by.guretsky.task2_threads.exception with the specified
     * detail message.
     *
     * @param message detail message
     */
    public FileDoesNotExistException(final String message) {
        super(message);
    }
}
