package TK;

import java.io.IOException;
import java.util.List;
import TK.Status.*;

import static TK.utility.*;

public class TaskList {
    public List<Task> currentTasks;

    public TaskList() throws IOException {
        // load existing tasks on startup
        this.currentTasks = utility.loadTasks(false);
    }

    public boolean anyComplete() {
        // checks for at least one completed task
        return this.currentTasks.stream().anyMatch(Task::isComplete);
    }

    public void add() {
        /*
         prompts the user to enter a String for a new Task description,
         creates a uncompleted new task with user input
         saves changes to file before ending
         */
        printTop();
        printBordered("Please enter a description of your new task!");
        printBottom();
        List<Task> newTaskList = this.currentTasks;
        Task newTask = new Task(getStringInput(), Status.Uncompleted); // new tasks are always uncompleted
        newTaskList.add(newTask);
        storeTasks(newTaskList); // save changes
        printTop();
        printBordered("New Task Added!");
    }

    public void edit() {
        /*
         prompts the user to choose a task to edit,
         further prompts to edit the description or flip the completion status
         edit description -> set description to next user input
         flip status -> (uncomplete -> complete || complete -> uncomplete)
         */
        printTop();
        printBordered("What Task would you like to Edit?");
        displayTasks();
        printBordered("99. Exit");
        printBottom();
        System.out.print(currentTasks.size());
        int input = getIntInput(currentTasks.size());
        if (input == 99) {
            printBorderless("Exiting Edit Menu...");
        } else {
            Task task = this.currentTasks.get(input - 1);
            printTop();
            printBordered("What would you like to change?");
            printBordered("1. Edit Description");
            printBordered("2. Mark " + (task.isComplete() ? "Uncomplete" : "Complete")); // flip current status
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
                    if (task.isComplete()) {
                        task.status = Status.Uncompleted;
                    } else {
                        task.status = Status.Completed;
                    }
                }
                default:
                    break;
            }
        }
    }

    public void delete() {
        /*
         prompts the user to choose a task to delete,
         removes that task from a copy of currentTasks
         saves the new list over the storedtasks file
         */
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
            int confirmation = getIntInput(2);
            if (confirmation == 1) { // else: 2 == exit
                List<Task> newTaskList = this.currentTasks;
                newTaskList.remove(this.currentTasks.get(input - 1));
                storeTasks(newTaskList);
                printTop();
                printBordered("Task Successfully Deleted!");
            }
        }
        printBordered("Exiting Delete Menu...");

    }

    public void removeCompleted() {
        /*
         prompts the user if they want to delete all completed tasks,
         removed all task from a copy of currentTasks where isCompelte is true
         saves the new list over the storedtasks file
         */
        printTop();
        printBordered("Are you sure you want to clear ALL Completed Tasks?");
        printBordered("1. Yes");
        printBordered("2. No");
        printBottom();

        switch (getIntInput(2)) {
            case 1: {
                List<Task> newTaskList = this.currentTasks;
                newTaskList.removeIf(Task::isComplete); // removes tasks if they are completed
                //  ^^^ my IDE suggested this function, idk if we need to state that when it shows us new functions

                storeTasks(newTaskList);
                printBorderless("Completed Tasks Successfully Deleted!");
            }
            case 2: {
                printBorderless("Exiting Remove Completed Menu...");
            }
        }
    }

    public void displayTasks() {
        // Displays all current tasks in a numbers list
        for (Task task : currentTasks) {
            printBordered((this.currentTasks.indexOf(task) + 1) + ". " + task);
        }
        printEmptyBorder();
    }
}
