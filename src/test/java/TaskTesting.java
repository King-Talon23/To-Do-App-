import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import TK.Task;
import TK.Task.*;

public class TaskTesting {

    private Task incompleteTask;
    private Task completeTask;

    @BeforeEach
    void setUp() {
        // testing objects
        incompleteTask = new Task("Write CSD318 Essay", false);
        completeTask = new Task("Code To-Do App", true);
    }

    @Test
    void testCorrectlyInitializes() {
        // Verifies correct initialization completed and incompleted tasks
        assertEquals("Write report", incompleteTask.description);
        assertFalse(incompleteTask.isComplete);
        assertEquals("[]", incompleteTask.status);

        assertEquals("Submit project", completeTask.description);
        assertTrue(completeTask.isComplete);
        assertEquals("{X}", completeTask.status);
    }

    @Test
    void testCompleteToIncomplete() {
        // checks complete() works correctly
        incompleteTask.complete();
        assertTrue(incompleteTask.isComplete);
        assertEquals("{X}", incompleteTask.status);
    }

    @Test
    void testIncompleteToComplete() {
        // checks decomplete() works correctly changes
        completeTask.decomplete();
        assertFalse(completeTask.isComplete);
        assertEquals("[]", completeTask.status);
    }

    @Test
    void testToStringReturnsFormat() {
        // Verify string representation follows "status: description"
        assertEquals("[]: Write CSD318 Essay", incompleteTask.toString());
        assertEquals("{X}: Code To-Do App", completeTask.toString());
    }

    @Test
    void testStatusSymbolMappingConsistency() {
        // Verify that status symbols match expected mapping logic
        incompleteTask.complete();
        assertEquals("{X}", incompleteTask.status);
        incompleteTask.decomplete();
        assertEquals("[]", incompleteTask.status);
    }
}
