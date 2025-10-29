package TK;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class storeTasks {
    public static void storeTasks(Task[] array) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StoredTasks.txt"))) {
            for (int i = 0; i < array.length; i++) {
                writer.write(array[i].toString());
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
        List<Task> tasks = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader("StoredTasks.txt"));

        String line;
        while ((line = bf.readLine()) != null) {
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


}
