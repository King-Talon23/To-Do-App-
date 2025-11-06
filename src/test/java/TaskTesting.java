import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import TK.Task;

public class TaskTesting {

    private Task uncompleteTask;
    private Task completeTask;

    @BeforeEach
    void setUp() {
        // testing objects
        uncompleteTask = new Task("Write CSD318 Essay", false);
        completeTask = new Task("Code To-Do App", true);
    }

    @Test
    void testCorrectlyInitializes() {
        // makes sure initialization testing tasks initialized properly
        assertEquals("Write CSD318 Essay", uncompleteTask.description);
        assertFalse(uncompleteTask.isComplete);
        assertEquals("[]", uncompleteTask.status);

        assertEquals("Code To-Do App", completeTask.description);
        assertTrue(completeTask.isComplete);
        assertEquals("{X}", completeTask.status);
    }

    @Test
    void testToStringReturns() {
        // check string representation follows "status: description"
        assertEquals("[]: Write CSD318 Essay", uncompleteTask.toString());
        assertEquals("{X}: Code To-Do App", completeTask.toString());
    }

    @Test
    void testStatusSymbolChanges() {
        // check that status symbols changes correctly after complete/uncomplete()
        uncompleteTask.complete();
        assertEquals("{X}", uncompleteTask.status);
        assertTrue(uncompleteTask.isComplete);

        completeTask.uncomplete();
        assertEquals("[]", completeTask.status);
        assertFalse(completeTask.isComplete);

    }
}
