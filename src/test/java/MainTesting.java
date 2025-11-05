
import TK.Task;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;
import TK.Main;

import static org.junit.jupiter.api.Assertions.*;

public class MainTesting {

    private final PrintStream sysOutBackup = System.out;
    private final InputStream sysInBackup = System.in;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // reset static fields
        Main.running = true;
    }

    @AfterEach
    void tearDown() {
        System.setOut(sysOutBackup);
        System.setIn(sysInBackup);
    }

    @Test
    void testStaticInitializationOfTaskList() {
        assertNotNull(Main.tasklist, "TaskList should initialize in static block");
    }


    @Test
    void testExit() {
        List<Task> testTasks = new ArrayList<>(List.of(new Task("Test", false)));
        Main.tasklist.currentTasks = testTasks;

        Main.running = true;
        invokeExit();

        assertFalse(Main.running, "Exit should set running to false");
        String output = outContent.toString();
        assertTrue(output.contains("Tasks Saved!") && output.contains("Goodbye!"));
    }

    private void invokeExit() {
        try {
            var method = Main.class.getDeclaredMethod("exit");
            method.setAccessible(true);
            method.invoke(null);
        } catch (Exception e) {
            fail("Exit method invocation failed: " + e.getMessage());
        }
    }


    @Test
    void testMainMenuDisplaysAndExits() {
        // simulate user input, immediately choose "99" to exit
        String userInput = "99\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        assertDoesNotThrow(() -> Main.main(new String[]{}));

        String output = outContent.toString();
        assertTrue(output.contains("Welcome back"));
        assertTrue(output.contains("Goodbye"));
    }

    @Test
    void testMainMenuWithCompletedTaskShowsRemoveOption() throws IOException {
        // prepare a completed task list
        Task completedTask = new Task("Done", true);
        Main.tasklist.currentTasks = new ArrayList<>(List.of(completedTask));

        // simulate input: immediately quit
        String userInput = "99\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        Main.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Remove ALL Completed Tasks"));
    }

    @Test
    void testRunningCanBeControlled() {
        Main.running = false;
        assertFalse(Main.running);
        Main.running = true;
        assertTrue(Main.running);
    }
}
