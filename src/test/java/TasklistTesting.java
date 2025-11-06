import TK.Task;
import TK.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TasklistTesting {
    private TaskList tasklist;

    @BeforeEach
    void setUp() throws IOException {
        tasklist = new TaskList();
        tasklist.currentTasks.clear(); // remove all real tasks
        tasklist.currentTasks.add(new Task("Task A", false));
        tasklist.currentTasks.add(new Task("Task B", true));
    }

    @Test
    void testLoadsTasksProperly() {
        // make sure tasks are loaded properly
        assertEquals(2, tasklist.currentTasks.size());
        assertEquals("Task A", tasklist.currentTasks.getFirst().description);
    }

    @Test
    void testAnyCompleteReturnsTrue() {
        assertTrue(tasklist.anyComplete());
    }

    @Test
    void testAnyCompleteReturnsFalse() {
        for (Task t : tasklist.currentTasks) t.uncomplete();
        assertFalse(tasklist.anyComplete());
    }

    @Test
    void testEdit() {
        String simulatedInput = "1\n1\nI am a new Description!"; // new description
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        tasklist.add();

        assertTrue(tasklist.currentTasks.stream() // see if it was changed
                .anyMatch(t -> t.description.equals("I am a new Description!")));
    }

    @Test
    void testDelete() {
        tasklist.currentTasks.clear();
        tasklist.currentTasks.add(new Task("please delete", true));
        System.setIn(new ByteArrayInputStream("1\n1\n".getBytes()));
        TK.utility.sc = new Scanner(System.in);

        tasklist.delete();

        assertFalse(tasklist.currentTasks.stream().anyMatch(x -> x.description.equals("please delete")));
    }

    @Test
    void testAdd() {
        tasklist.currentTasks.clear();

        String deleteInput = "I AM NEW TASK!!!\n";
        System.setIn(new ByteArrayInputStream(deleteInput.getBytes()));

        tasklist.add();
        assertTrue(tasklist.currentTasks.stream().anyMatch(x -> x.description.equals("I AM NEW TASK!!!")));
    }

}

