import TK.Task;
import TK.TaskList;
import TK.utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static TK.utility.getIntInput;
import static TK.utility.storeTasks;

public class TasklistTesting {

    private List<Task> mockTasks;
    private TaskList taskList;

    @BeforeEach
    void setUp() throws IOException {
        // mock utility.loadTasks() to return a set list instead of making real I/O calls
        mockTasks = new ArrayList<>();
        mockTasks.add(new Task("Task A", false));
        mockTasks.add(new Task("Task B", true));

        try (MockedStatic<utility> mockedUtility = Mockito.mockStatic(utility.class)) {
            mockedUtility.when(utility::loadTasks).thenReturn(mockTasks);
            taskList = new TaskList();
        }
    }

    @Test
    void testConstructorLoadsTasks() {
        // make sure tasks are loaded properly
        assertEquals(2, taskList.currentTasks.size());
        assertEquals("Task A", taskList.currentTasks.get(0).description);
    }

    @Test
    void testAnyCompleteReturnsTrueAccuratly() {
        assertTrue(taskList.anyComplete());
    }

    @Test
    void testAnyCompleteReturnsFalseAccuratly() {
        for (Task t : taskList.currentTasks) t.decomplete();
        assertFalse(taskList.anyComplete());
    }

    @Test
    void testAddAndSave() {
        try (MockedStatic<utility> mockedUtility = Mockito.mockStatic(utility.class)) {
            mockedUtility.when(utility::loadTasks).thenReturn(mockTasks);
            mockedUtility.when(utility::getStringInput).thenReturn("New Mock Task");

            taskList.add();

            // was the new task added?
            assertTrue(taskList.currentTasks.stream()
                    .anyMatch(t -> t.description.equals("New Mock Task")));

            // was new tasklist was saved?
            mockedUtility.verify(() -> utility.storeTasks(any(List.class)));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
