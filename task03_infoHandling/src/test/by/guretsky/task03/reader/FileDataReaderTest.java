package test.by.guretsky.task03.reader;

import by.guretsky.task03.exception.FileException;
import by.guretsky.task03.reader.FileDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Test class for {@link FileDataReader} class.
 */
public class FileDataReaderTest {
    /**
     * Null file path.
     */
    private static final String NULL_FILE_PATH = null;
    /**
     * Correct file path.
     */
    private static final String FILE_PATH = "data" + File.separator
            + "test.txt";
    /**
     * {@link FileDataReader} object.
     */
    private FileDataReader reader;

    /**
     * Positive test method for {@link FileDataReader#readString()}.
     *
     * @throws FileException if file doesn't exist or file is empty
     */
    @Test(description = "Positive script for file data reader method")
    public void testReadStringList() throws FileException {
        reader = new FileDataReader(FILE_PATH);
        String expected = "    It is a (111^5|1&2«(2|5»2&71))|1200 established"
                + " fact that a reader will be of a\n"
                + "page when looking at its layout.";
        String actual = reader.readString();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Negative test method for {@link FileDataReader#readString()}.
     *
     * @throws FileException if file doesn't exist or file is empty
     */
    @Test(description = "Negative script for file data reader method",
            expectedExceptions = {FileException.class})
    public void testReadStringList2() throws FileException {
        reader = new FileDataReader(NULL_FILE_PATH);
        String expected = "";
        String actual = reader.readString();
        Assert.assertEquals(actual, expected);
    }
}
