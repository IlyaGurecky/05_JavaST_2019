package test.task01_objects.validator;

import by.guretsky.task01_objects.exception.FileDoesNotExistException;
import by.guretsky.task01_objects.reader.FileDataReader;
import by.guretsky.task01_objects.validator.DataFilter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFilterTest {
    private static final String FILE_PATH = "data" + File.separator
            + "info.txt";

    @Test(description = "Positive script for file data validator")
    public void testFilterFileData() throws FileDoesNotExistException {
        FileDataReader fileDataReader = new FileDataReader(FILE_PATH);
        List<String> filterData = fileDataReader.readStringList();
        DataFilter dataFilter = new DataFilter();
        List<String> actual = dataFilter.filterFileData(filterData);
        List<String> expected = new ArrayList<>(Arrays
                .asList("2 2 2 -2 -2 -2 -2 2",
                        "2 3 4 5 6 7 8 1"));

        Assert.assertEquals(actual, expected);
    }
}
