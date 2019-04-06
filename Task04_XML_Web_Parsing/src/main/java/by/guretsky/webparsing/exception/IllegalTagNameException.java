package by.guretsky.webparsing.exception;

/**
 * Illegal tag name exception class.
 */
public class IllegalTagNameException extends Exception {
    /**
     * Constructor - constructs exception without parameters.
     */
    public IllegalTagNameException() {
    }

    /**
     * Constructor - constructs exception with special message.
     *
     * @param message special message
     */
    public IllegalTagNameException(final String message) {
        super(message);
    }
}
