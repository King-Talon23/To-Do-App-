import TK.Task;
import TK.utility;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTesting {

    private final InputStream sysInBackup = System.in;
    private final PrintStream sysOutBackup = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // reset static counters before each new test
        utility.borderSegment = 0;
        utility.segmentSection = 0;
    }

    @AfterEach
    void tearDown() {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    @Test
    void testStoreAndLoadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Alpha", false));
        tasks.add(new Task("Beta", true));

        // Redirect the output file to a temp file
        Path tempFile = Files.createTempFile("tasks", ".txt");
        File oldFile = new File("StoredTasks.txt");
        File tempCopy = tempFile.toFile();
        tempCopy.deleteOnExit();

        // Temporarily replace file name by renaming
        Files.move(oldFile.toPath(), Paths.get("StoredTasks_backup.txt"), StandardCopyOption.REPLACE_EXISTING);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StoredTasks.txt"))) {
            for (Task t : tasks) {
                writer.write(t.toString());
                writer.newLine();
            }
        }

        List<Task> loaded = utility.loadTasks();

        assertEquals(2, loaded.size());
        assertEquals("Alpha", loaded.get(0).description);
        assertEquals("Beta", loaded.get(1).description);
        assertFalse(loaded.get(0).isComplete);
        assertTrue(loaded.get(1).isComplete);
    }

    @Test
    void testGetIntInputValid() {
        String userInput = "2\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        int result = utility.getIntInput(3);
        assertEquals(2, result);
    }

    @Test
    void testGetIntInputInvalidThenValid() {
        String userInput = "hello\n5\n"; // invalid(hello) then valid(5)
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        int result = utility.getIntInput(5);
        assertEquals(5, result);
    }

    @Test
    void testGetStringInputSkipsEmpty() {
        String userInput = "\n  \nValidInput\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String result = utility.getStringInput();
        assertEquals("ValidInput", result);
    }

    @Test
    void testAdvanceBorderWrapsAround() {
        // Simulate multiple calls to advance through all borders
        for (int i = 0; i < 25; i++) {
            utility.printEmptyBorder();
        }
        assertTrue(utility.borderSegment >= 0 && utility.borderSegment < 4);
        assertTrue(utility.segmentSection >= 0 && utility.segmentSection < 5);
    }

}


