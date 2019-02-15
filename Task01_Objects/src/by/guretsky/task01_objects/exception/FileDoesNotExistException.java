package by.guretsky.task01_objects.exception;

import java.io.IOException;

public class FileDoesNotExistException extends Exception {
    public FileDoesNotExistException(String message) {
        super(message);
    }

    public FileDoesNotExistException() {
    }
}
