import TK.Status;
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
        uncompleteTask = new Task("Write CSD318 Essay", Status.Uncompleted);
        completeTask = new Task("Code To-Do App", Status.Completed);
    }

    @Test
    void testCorrectlyInitializes() {
        // makes sure initialization testing tasks initialized properly
        assertEquals("Write CSD318 Essay", uncompleteTask.description);
        assertFalse(uncompleteTask.isComplete());
        assertEquals(Status.Uncompleted, uncompleteTask.status);

        assertEquals("Code To-Do App", completeTask.description);
        assertTrue(completeTask.isComplete());
        assertEquals(Status.Completed, completeTask.status);
    }

    @Test
    void testToStringReturns() {
        // check string representation follows "status: description"
        assertEquals("[]: Write CSD318 Essay", uncompleteTask.toString());
        assertEquals("{X}: Code To-Do App", completeTask.toString());
    }
}
