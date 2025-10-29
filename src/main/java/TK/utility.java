package TK;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class utility {
    public static void storeTasks(Task[] array) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StoredTasks.txt"))) {
            for (int i = 0; i < array.length; i++) {
                writer.write(String.valueOf(array[i]));
                if (i < array.length - 1) {
                    writer.write("\n");
                }
            }
            writer.newLine();
            System.out.println("Tasks Saved.");
        } catch (IOException e) {
            System.err.println("Error writing array to file: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks() throws IOException {
        // reads all lines from file and converts them back from String to Task
        List<Task> tasks = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader("StoredTasks.txt"));
        String line;

        while ((line = bf.readLine()) != null) { // avoids last line
            String[] splitTasks = line.split(":");
            if (splitTasks.length == 2) {
                String name = splitTasks[1].trim();
                boolean completed = Boolean.parseBoolean(splitTasks[0].trim());
                tasks.add(new Task(name, completed));
            }
        }
        bf.close();
        return tasks;
    }

    public int getInput(int max) {
        int input = 0;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextInt();
        } while (input < 1 || input > max);

        return input;

    }
}
