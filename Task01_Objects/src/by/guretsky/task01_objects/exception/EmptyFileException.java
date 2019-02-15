package by.guretsky.task01_objects.exception;

public class EmptyFileException extends Exception {
    public EmptyFileException() {
    }

    public EmptyFileException(String message) {
        super(message);
    }
}
