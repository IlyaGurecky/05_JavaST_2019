package by.guretsky.task2_threads.reader;

import by.guretsky.task2_threads.exception.EmptyFileException;
import by.guretsky.task2_threads.exception.FileDoesNotExistException;
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

/**
 * Include method to read data from file.
 */
public class FileDataReader {
    /**
     * Logger, which used to log event.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(FileDataReader.class);
    /**
     * Need to work with files.
     */
    private Path path;
    /**
     * List of the file lines.
     */
    private List<String> stringList = new ArrayList<>();

    /**
     * Private constructor.
     *
     * @see FileDataReader#FileDataReader(String)
     */
    private FileDataReader() {
    }

    /**
     * Constructor - to create object with parameters.
     *
     * @param filePath path to the file
     * @throws FileDoesNotExistException if file doesn't exist
     */
    public FileDataReader(final String filePath) throws
            FileDoesNotExistException {
        if (filePath != null) {
            path = Paths.get(filePath);
        } else {
            throw new FileDoesNotExistException("Path argument is null");
        }
    }

    /**
     * The method reads information from file and collect to the list.
     *
     * @return list of the file lines
     */
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
