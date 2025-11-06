package TK;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static TK.utility.*;

public class Main {
    public static boolean running = true;
    public static TaskList tasklist;
    static {
        try {
            tasklist = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Map<Integer, Runnable> inputMap = new HashMap<>(Map.of(
            // maps all possible user input to actions
            1, tasklist::add,
            2, tasklist::edit,
            3, tasklist::delete,
            4, tasklist::removeCompleted,
            99, Main::exit));

    public static void main(String[] args) {
        printTop();
        printBordered("Welcome back, here's your tasks.");
        printEmptyBorder();

        while (running) {
            int maxInput = 3; // add/edit/delete
            tasklist.displayTasks();
            printBordered("What would you like to do?");
            printBordered("1. Add a Task");
            printBordered("2. Edit/Complete a Task");
            printBordered("3. Delete a Task");

            if (tasklist.anyComplete()) {
                // if any tasks are completed the option to remove completed tasks
                maxInput++;
                printBordered("4. Remove ALL Completed Tasks");
            }
            printBordered("99. Save & Quit");
            printBottom();

            inputMap.get(getIntInput(maxInput)).run(); // run the matching function from user input map
        }
    }

    public static void exit() {
        running = false;
        storeTasks(tasklist.currentTasks); // saves current tasks before quitting
        printBorderless("Tasks Saved!");
        printBorderless("Goodbye!");
    }
}
