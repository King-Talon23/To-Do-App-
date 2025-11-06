import TK.Task;
import TK.utility;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTesting {
    private ByteArrayOutputStream outContent;

    @Test
    void testStoreAndLoadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task One", false));
        tasks.add(new Task("Task Two", true));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StoredTasks_test.txt"))) {
            for (Task t : tasks) {
                writer.write(t.toString());
                writer.newLine();
            }
        }

        List<Task> loaded = utility.loadTasks(true);

        assertEquals(2, loaded.size());
        assertEquals("Task One", loaded.get(0).description);
        assertEquals("Task Two", loaded.get(1).description);
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
        String userInput = "hello\n5\n"; // invalid(hello) valid(5)
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

}


