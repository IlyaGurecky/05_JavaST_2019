package by.guretsky.task01_objects.reader;

import by.guretsky.task01_objects.exception.EmptyFileException;
import by.guretsky.task01_objects.exception.FileDoesNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDataReader {
    private static final Logger LOGGER = LogManager
            .getLogger(FileDataReader.class);
    private Path path;
    private List<String> stringList = new ArrayList<>();

    private FileDataReader() {
    }

    public FileDataReader(final String filePath) throws
            FileDoesNotExistException {
        if (filePath != null) {
            path = Paths.get(filePath);
        } else {
            throw new FileDoesNotExistException("Path argument is null");
        }
    }

    public List<String> readStringList() {
        try (Stream<String> stringStream = Files.lines(path)) {
            stringList = stringStream.collect(Collectors.toList());
            if (stringList.isEmpty()) {
                throw new EmptyFileException("File is empty");
            }
        } catch (IOException e) {
            LOGGER.error("File doesn't exist", e);
        } catch (EmptyFileException e) {
            LOGGER.error("File is empty", e);
        } catch (UncheckedIOException e) {
            LOGGER.error("File path is a directory", e);
        }
        return stringList;
    }
}
