package test.task01_objects.reader;

import by.guretsky.task01_objects.exception.FileDoesNotExistException;
import by.guretsky.task01_objects.reader.FileDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for {@link FileDataReader}.
 */
public class FileDataReaderTest {
    /**
     * Path of the info file.
     */
    private static final String FILE_PATH = "data" + File.separator
            + "test.txt";
    /**
     * {@link FileDataReader} object.
     */
    private FileDataReader fileDataReader;

    /**
     * Negative test method for {@link FileDataReader#readStringList()}.
     *
     * @throws FileDoesNotExistException if file doesn't exist
     */
    @Test(description = "Negative script when file path is null",
            expectedExceptions = FileDoesNotExistException.class)
    public void testReadStringList1() throws FileDoesNotExistException {
        fileDataReader = new FileDataReader(null);
        List<String> actualInfo = fileDataReader.readStringList();
        List<String> expected = new ArrayList<>(Arrays.asList("2", "3"));

        Assert.assertEquals(actualInfo, expected);
    }

    /**
     * Positive test method for {@link FileDataReader#readStringList()}.
     *
     * @throws FileDoesNotExistException if file doesn't exist
     */
    @Test(description = "Positive script for reader")
    public void testReadStringList2() throws FileDoesNotExistException {
        fileDataReader = new FileDataReader(FILE_PATH);
        List<String> actualInfo = fileDataReader.readStringList();
        List<String> expected = new ArrayList<>(Arrays.asList("1234567",
                "54321", "hello"));

        Assert.assertEquals(actualInfo, expected);
    }
}
