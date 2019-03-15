package by.guretsky.task03.reader;

import by.guretsky.task03.exception.FileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Data reader class. Includes method to read data from file.
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
     * Constructor - to create object with parameters.
     *
     * @param filePath path to the file
     * @throws FileException if argument is null
     */
    public FileDataReader(final String filePath) throws
            FileException {
        if (filePath != null) {
            path = Paths.get(filePath);
        } else {
            throw new FileException("Path argument is null");
        }
    }

    /**
     * The method reads information from file to String.
     *
     * @return string of the file data
     */
    public String readString() {
        String fileData = "";
        try {
            fileData = Files.readString(path);
            if (fileData.isEmpty()) {
                throw new FileException("File is empty");
            }
        } catch (IOException e) {
            LOGGER.error("File doesn't exist", e);
        } catch (FileException e) {
            LOGGER.error("File is empty", e);
        }
        return fileData;
    }
}
