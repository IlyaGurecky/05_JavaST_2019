package by.guretsky.task2_threads.exception;

/**
 * Signals that file is empty.
 */
public class EmptyFileException extends Exception {

    /**
     * Constructs a new by.guretsky.task2_threads.exception.
     */
    public EmptyFileException() {
        super();
    }

    /**
     * Constructs a new by.guretsky.task2_threads.exception with the specified detail message.
     *
     * @param message detail message
     */
    public EmptyFileException(String message) {
        super(message);
    }
}
