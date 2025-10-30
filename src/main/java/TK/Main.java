package TK;

import java.io.IOException;
import java.util.List;
import static TK.utility.getIntInput;
import static TK.utility.print;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean running = true;
        TaskList tasklist = new TaskList();
        int maxInput = 3;
        if (tasklist.anyComplete) {
            maxInput += 1; // remove completed tasks
        }
        print("Welcome back, here's your tasks.");
        tasklist.displayTasks();
        while (running) {
            print("What would you like to do?");
            print("1. Add");
            print("2. Edit");
            print("3. Delete");
            if (tasklist.anyComplete) {
                print("4. Remove Completed Tasks");
            }




            switch (getIntInput(maxInput)) {
                case 1: {
                    tasklist.add();
                }
                case 2: {
                    tasklist.edit();
                }
                case 3: {
                    tasklist.delete();
                }
                case 4: {
                    tasklist.removeCompleted();
                }
                case 99: running = false;
            }
        }
w
    }

    private static void printMainMenu(TaskList tasklist) {

    }

    private static void mainMenuLogic(TaskList tasklist) {

    }

}