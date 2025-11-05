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

    public static void main(String[] args) {
        int maxInput = 3; // add/edit/delete

        // map all possible user input to actions
        Map<Integer, Runnable> inputMap = new HashMap<>(Map.of(
                1, tasklist::add,
                2, tasklist::edit,
                3, tasklist::delete,
                4, tasklist::removeCompleted,
                99, Main::exit));
        printTop();
        printBordered("Welcome back, here's your tasks.");
        printEmptyBorder();

        while (running) {
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

            inputMap.get(getIntInput(maxInput)).run(); // run the corresponding function based off user input
        }
    }

    private static void exit() {
        running = false;
        utility.storeTasks(tasklist.currentTasks); // saves current tasks before quitting
        printBordered("Tasks Saved!");
        printBordered("Goodbye!");
    }
}
