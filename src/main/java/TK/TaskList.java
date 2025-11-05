package TK;

import java.io.IOException;
import java.util.List;

import static TK.utility.*;

public class TaskList {
    List<Task> currentTasks;

    public TaskList() throws IOException {
        // load existing tasks on startup
        this.currentTasks = utility.loadTasks();
    }

    public boolean anyComplete() {
        // check for at atleast one completed task
        for (Task task : this.currentTasks) {
            if (task.isComplete) {
                return true;
            }
        }
        return false;
    }

    public void add() {
        printTop();
        printBordered("Please enter a description of your new task!");
        printBottom();
        List<Task> newTaskList = this.currentTasks;
        Task newTask = new Task(getStringInput(), false);
        newTaskList.add(newTask);
        utility.storeTasks(newTaskList); // save changes right after addition
        printTop();
        printBordered("New Task Added!");
    }

    public void edit() {
        printTop();
        printBordered("What Task would you like to Edit?");
        displayTasks();
        printBordered("99. Exit");
        printBottom();

        int input = getIntInput(currentTasks.size());
        if (input == 99) {
            printBorderless("Exiting Edit Menu...");
        } else {
            Task task = this.currentTasks.get(input - 1);
            printTop();
            printBordered("What would you like to change?");
            printBordered("1. Edit Description");
            printBordered("2. Mark " + (task.isComplete ? "Decomplete" : "Complete"));
            printBordered("99. Quit");
            printBottom();

            switch (getIntInput(2)) {
                case 1: {
                    printBorderless("Enter a new task Description:");
                    task.description = getStringInput();
                    printBorderless("Description Changed Successfully!");
                }
                case 2: {
                    // flip current completion status
                    if (task.isComplete) {
                        task.decomplete();
                    } else {
                        task.complete();
                    }
                }
                default:
                    break;
            }
        }
    }

    public void delete() {
        // prompts the user to choose a task to delete,
        printTop();
        printBordered("What Task would you like to Delete?");
        displayTasks();
        printBordered("99. Exit");
        printBottom();

        int input = getIntInput(currentTasks.size());
        if (input != 99) { // else: 99 == exit
            printTop();
            printBordered("Are you sure you want to delete this task?");
            printBordered("1. Yes");
            printBordered("2. No");
            printBottom();

            if (getIntInput(2) == 1) { // else: 2 == exit
                List<Task> newTaskList = this.currentTasks;
                newTaskList.remove(this.currentTasks.get(input - 1));
                storeTasks(newTaskList); // save new tasklist
                printTop();
                printBordered("Task Successfully Deleted!");
            }
        }
        printBordered("Exiting Delete Menu...");

    }

    public void removeCompleted() {
        printTop();
        printBordered("Are you sure you want to clear ALL Completed Tasks?");
        printBordered("1. Yes");
        printBordered("2. No");
        printBottom();

        switch (getIntInput(2)) {
            case 1: {
                List<Task> newTaskList = this.currentTasks;
                newTaskList.removeIf(task -> task.isComplete); // removes all completed tasks from the list
                // ^^^ my IDE suggested this function, idk if we need to state that when it shows us new functions

                utility.storeTasks(newTaskList); // save updated list
                printBorderless("Completed Tasks Successfully Deleted!");
            }
            case 2: {
                printBorderless("Exiting Remove Completed Menu...");
            }
        }
    }

    public void displayTasks() {
        for (Task task : currentTasks) {
            printBordered((this.currentTasks.indexOf(task) + 1) + ". " + task);
        }
        printEmptyBorder();
    }
}
