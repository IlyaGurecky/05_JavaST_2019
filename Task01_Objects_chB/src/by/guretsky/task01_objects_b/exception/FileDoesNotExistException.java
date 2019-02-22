package by.guretsky.task01_objects_b.exception;

/**
 * Signals that file doesn't exist.
 */
public class FileDoesNotExistException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message detail message
     */
    public FileDoesNotExistException(final String message) {
        super(message);
    }
}
