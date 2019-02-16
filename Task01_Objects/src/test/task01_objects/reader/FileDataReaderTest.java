package test.task01_objects.reader;

import by.guretsky.task01_objects.exception.FileDoesNotExistException;
import by.guretsky.task01_objects.reader.FileDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileDataReaderTest {
    private static final String FILE_PATH = "data" + File.separator
            + "test.txt";
    private FileDataReader fileDataReader;

    @Test(description = "Negative script when file path is null",
            expectedExceptions = FileDoesNotExistException.class)
    public void testReadStringList1() throws FileDoesNotExistException {
        fileDataReader = new FileDataReader(null);
        List<String> actualInfo = fileDataReader.readStringList();
        List<String> expected = new ArrayList<>(Arrays.asList("2", "3"));

        Assert.assertEquals(actualInfo, expected);
    }

    @Test(description = "Positive script for reader")
    public void testReadStringList2() throws FileDoesNotExistException {
        fileDataReader = new FileDataReader(FILE_PATH);
        List<String> actualInfo = fileDataReader.readStringList();
        List<String> expected = new ArrayList<>(Arrays.asList("1234567",
                "54321", "hello"));

        Assert.assertEquals(actualInfo, expected);
    }
}
