package by.guretsky.task01_objects.exception;

public class FileDoesNotExistException extends Exception {
    public FileDoesNotExistException(final String message) {
        super(message);
    }

    public FileDoesNotExistException() {
    }
}
