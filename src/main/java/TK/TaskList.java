package TK;

import java.io.IOException;
import java.util.List;

import static TK.utility.*;

public class TaskList {
    List<Task> currentTasks;

    public TaskList() throws IOException {
        this.currentTasks = utility.loadTasks();
    }

    private void add() {
        print("Please enter a description of your new task!");
        List<Task> newTaskList = this.currentTasks;
        Task newTask = new Task(getStringInput(), false);
        newTaskList.add(newTask);
        utility.storeTasks(newTaskList);
        print("New Task Added!");
    }

    public void edit() {
        print("What Task would you like to Edit?");
        displayTasks();
        int input = getIntInput(currentTasks.size());
        if (input == 99) {
            print("Exiting Edit Menu...");
        } else {
            Task task = this.currentTasks.get(input - 1);
            print("What would you like to change?");
            print("1. Edit Description");
            print("2. Mark " + (task.isComplete ? "Completed" : "Uncomplete"));
            print("99. Quit");
            switch (getIntInput(2)) {
                case 1: {
                    print("Enter a new task Description:");
                    task.description = getStringInput();
                    print("Description Changed Sucessfully!");
                }
                case 2: {
                    if (task.isComplete) {
                        task.decomplete();
                        print("Task Decompeleted. :(");
                    } else {
                        task.complete();
                        print("Task Complete!");
                    }
                }
            }
        }
    }

    public void delete() {
        print("What Task would you like to Delete?");
        displayTasks();
        int input = getIntInput(currentTasks.size());
        if (input == 99) {
            print("Exiting Delete Menu...");
        } else {
            Task task = this.currentTasks.get(input - 1);
            print("Are you sure you want to delete this task?");
            print("1. Yes");
            print("2. No");
            switch (getIntInput(2)) {
                case 1: {
                    List<Task> newTaskList = this.currentTasks;
                    newTaskList.remove(task);
                    utility.storeTasks(newTaskList);
                    print("Task Successfully Deleted!");
                }
                case 2: {
                    print("Exiting Delete Menu...");
                }
            }
        }
    }

    public void removeCompleted() {
        print("Are you sure you want to clear ALL Completed Tasks?");
        print("1. Yes");
        print("2. No");
        switch (getIntInput(2)) {
            case 1: {
                List<Task> newTaskList = this.currentTasks;
                newTaskList.removeIf(task -> task.isComplete);
                utility.storeTasks(newTaskList);
                print("Tasks Successfully Deleted!");
            }
            case 2: {
                print("Exiting Completion Removal Menu...");
            }
        }
    }


    private void displayTasks() {
        for (Task task : currentTasks) {
            print(currentTasks.indexOf(task) + ". " + task);
        }
    }
}
