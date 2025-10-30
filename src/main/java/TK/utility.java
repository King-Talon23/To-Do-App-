package TK;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class utility {
    public static void storeTasks(List<Task> array) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StoredTasks.txt"))) {
            for (int i = 0; i < array.size(); i++) {
                writer.write(String.valueOf(array.get(i)));
                if (i < array.size() - 1) {
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
                if (splitTasks[0].trim().equals("{X}")) {
                    tasks.add(new Task(name, true));
                } else {
                    tasks.add(new Task(name, false));
                }
            }
        }
        bf.close();
        return tasks;
    }

    public static int getIntInput(int max) {
        List<Integer> allowedNums = new ArrayList<>();
        for (int i=1; i > max; i++) {
            allowedNums.add(i);
        }
        allowedNums.add(99);
        int input = 0;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextInt();
        } while (allowedNums.contains(input));

        return input;

    }
    public static String getStringInput() {
        String input = "";
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextLine();
        } while (input.isEmpty());

        return input;
    }

    public static void print(String text) {
        String space = " ";
        int spacingAmount = 100 - text.length();
        if (spacingAmount / 2 != 0) {
            spacingAmount += 1;
        }
        String spaceString = space.repeat(spacingAmount / 2);
        System.out.print("|" + spaceString + text + spaceString + "|\n");

    }

}
