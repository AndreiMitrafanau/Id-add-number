import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FindAndChangeTest {
    @After
    public void after() throws IOException {
        // don't use file extension
        rollBackFileChanges("src/test/resources/oneLine");
        rollBackFileChanges("src/test/resources/withoutId");
        rollBackFileChanges("src/test/resources/multiLine");
    }

    private void rollBackFileChanges(String pathToFile) throws IOException {
        String suffix = "Initial";
        String fileExtension = ".txt";
        Path from = Paths.get(String.join("", pathToFile, suffix, fileExtension));
        Path to = Paths.get(String.join("",pathToFile, fileExtension));
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void couldAddOneToIdInTheFile() throws IOException {
        FindAndChange.apply("src/test/resources/oneLine.txt");

        List<String> actualList = Files.readAllLines(Paths.get("src/test/resources/oneLine.txt"));
        String actual = actualList.get(0).substring(16, 17);

        assertEquals(actual, "1");
    }

    @Test
    public void couldAddTwoToIdInTheFile() throws IOException {
        FindAndChange.apply("src/test/resources/oneLine.txt", "2");

        List<String> actualList = Files.readAllLines(Paths.get("src/test/resources/oneLine.txt"));
        String actual = actualList.get(0).substring(16, 17);

        assertEquals("2", actual);
    }

    @Test
    public void couldSubtractTwoToIdInTheFile() throws IOException {
        FindAndChange.apply("src/test/resources/oneLine.txt", "-2");

        List<String> actualList = Files.readAllLines(Paths.get("src/test/resources/oneLine.txt"));
        String actual = actualList.get(0).substring(16, 18);
        assertEquals("-2", actual);
    }

    @Test
    public void doNothingIfFileHaveNoId() throws IOException {
        FindAndChange.apply("src/test/resources/withoutId.txt", "-2");

        List<String> actualList = Files.readAllLines(Paths.get("src/test/resources/withoutId.txt"));
        String actual = actualList.get(0);

        assertEquals("Here is no id to replace", actual);
    }

    @Test
    public void couldModifyMultiLineFile() throws IOException {
        FindAndChange.apply("src/test/resources/multiLine.txt", "2");
        List<String> actualList = Files.readAllLines(Paths.get("src/test/resources/multiLine.txt"));

        assertEquals(4, actualList.size());

        String lineOne = actualList.get(0).substring(16, 17);
        String lineTwo = actualList.get(1);
        String lineThree = actualList.get(2);
        String lineFour = actualList.get(3).substring(16, 17);

        assertEquals("2", lineOne);
        assertEquals("Here is no id to replace", lineTwo);
        assertEquals("", lineThree);
        assertEquals("2", lineFour);
    }

    @Test
    public void couldWorkWithNegativeId() throws IOException {
        FindAndChange.apply("src/test/resources/multiLine.txt", "-2");
        // here we check that regexp could capture negative values
        FindAndChange.apply("src/test/resources/multiLine.txt", "-2");
        List<String> actualList = Files.readAllLines(Paths.get("src/test/resources/multiLine.txt"));

        assertEquals(4, actualList.size());

        String lineOne = actualList.get(0).substring(16, 18);
        String lineTwo = actualList.get(1);
        String lineThree = actualList.get(2);
        String lineFour = actualList.get(3).substring(16, 18);

        assertEquals("-4", lineOne);
        assertEquals("Here is no id to replace", lineTwo);
        assertEquals("", lineThree);
        assertEquals("-4", lineFour);
    }
}