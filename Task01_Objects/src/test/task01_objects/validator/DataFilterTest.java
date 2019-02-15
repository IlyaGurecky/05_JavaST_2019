package test.task01_objects.validator;

import by.guretsky.task01_objects.exception.FileDoesNotExistException;
import by.guretsky.task01_objects.reader.DataReader;
import by.guretsky.task01_objects.validator.DataFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFilterTest {
    private static final Logger LOGGER = LogManager
            .getLogger(DataFilterTest.class);
    private static final String FILE_PATH = "data" + File.separator
            + "info.txt";

    @Test(description = "Positive script for file data validator")
    public void testFilterFileData() throws FileDoesNotExistException {
        DataReader dataReader = new DataReader(FILE_PATH);
        List<String> filerData = dataReader.readStringList();
        DataFilter dataFilter = new DataFilter();
        List<String> actual = dataFilter.filterFileData(filerData);
        List<String> expected = new ArrayList<>(Arrays
                .asList("2 2 2 -2 -2 -2 -2 2",
                        "2 3 4 5 6 7 8 1"));

        Assert.assertEquals(actual, expected);
    }
}
