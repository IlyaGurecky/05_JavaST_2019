package by.guretsky.task03.exception;

/**
 * Signals about file error.
 */
public class FileException extends Exception {

    /**
     * Constructs a new exception.
     */
    public FileException() {
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message detail message
     */
    public FileException(final String message) {
        super(message);
    }
}
