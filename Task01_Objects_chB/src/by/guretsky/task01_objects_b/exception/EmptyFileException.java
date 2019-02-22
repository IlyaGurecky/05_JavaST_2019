package by.guretsky.task01_objects_b.exception;

/**
 * Signals that file is empty.
 */
public class EmptyFileException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message detail message
     */
    public EmptyFileException(final String message) {
        super(message);
    }
}
