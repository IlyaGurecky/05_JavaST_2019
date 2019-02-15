package test.task01_objects.reader;

import by.guretsky.task01_objects.exception.EmptyFileException;
import by.guretsky.task01_objects.exception.FileDoesNotExistException;
import by.guretsky.task01_objects.reader.DataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class DataReaderTest {
    private static final String FILE_PATH = "data" + File.separator
            + "info.txt";

    @Test(description = "Negative script when file path is null",
            expectedExceptions = FileDoesNotExistException.class)
    public void testReadStringList1() throws FileDoesNotExistException {
        DataReader dataReader = new DataReader(null);
        List<String> actualInfo = dataReader.readStringList();
        List<String> expected = new ArrayList<>(Arrays.asList("2", "3"));

        Assert.assertEquals(actualInfo, expected);
    }
}
