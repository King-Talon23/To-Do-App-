package TK;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class storeTasks {
    public static void storeTasks(Task[] array, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < array.length; i++) {
                writer.write(array[i].toString());
                if (i < array.length - 1) {
                    writer.write("\n");
                }
            }
            writer.newLine();
            System.out.println("Array written to text file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing array to text file: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks() throws IOException {
        List<Task> Tasks = new ArrayList<>();


        BufferedReader bf = new BufferedReader(new FileReader("StoredTasks.txt"));
        String line = bf.readLine();

        while (line != null) { // checking for last line
            String[] splitTasks = bf.readLine().split(":");
            if (Objects.equals(splitTasks[1], "true")) {
                Tasks.add(new Task(splitTasks[1], true));
            } else {
                Tasks.add(new Task(splitTasks[1], false));
            }
        }
        bf.close();
        return Tasks;
    }


}
