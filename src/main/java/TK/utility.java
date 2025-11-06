package TK;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class utility {
    // Track which border segment and section are currently being used for display
    public static int borderSegment = 0;
    public static int segmentSection = 0;

    // constants for spacing of borders and text
    static int BORDER_CENTER_AMOUNT = 18;
    static int BORDER_TEXT_CENTER = 100;
    static int BORDERLESS_TEXT_CENTER = 136;


    static String[][] border = { // border for in-terminal ui
            {"/\\/\\", "/\\--/\\", "<|(>  <)|>", "\\/--\\/", "\\/\\/"},
            {"/  \\", "/ /\\ \\", "\\ \\/ /", "\\  /", "//\\\\"},
            {"/  \\", "/ /\\ \\", "(  ()  )", "\\ \\/ /", "\\  /"},
            {"\\\\//", "/  \\", "/ /\\ \\", " \\ \\/ /", "\\  /"}
    };


    // file saving and loading
    public static void storeTasks(List<Task> tasklist) {
        // Writes a list of tasks to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StoredTasks.txt"))) {
            for (int i = 0; i < tasklist.size(); i++) {
                writer.write(tasklist.get(i).toString());
                if (i < (tasklist.size() - 1)) { writer.newLine(); }
            }
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing array to file: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks(boolean isTest) throws IOException {
        String filepath = isTest ? "StoredTasks_test.txt" : "StoredTasks.txt";
        // Loads tasks from file and reconstructs the list of Task objects
        List<Task> tasks = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader(filepath));
        String line;

        while ((line = bf.readLine()) != null) {
            String[] splitTasks = line.split(": ");
            if (splitTasks.length == 2) {
                String name = splitTasks[1].trim();
                tasks.add(new Task(name, splitTasks[0].trim().equals("{X}")));
            }
        }
        bf.close();
        return tasks;
    }

    // Input handliing
    public static int getIntInput(int max) {
        // requires the user enter an integer within the allowed range
        List<Integer> allowedNums = new ArrayList<>(List.of(99));
        for (int i = 1; i <= max; i++) allowedNums.add(i);

        Scanner sc = new Scanner(System.in);
        int input;
        do {
            System.out.print("> ");
            while (!sc.hasNextInt()) {
                System.out.print("Enter a number: ");
                sc.next();
            }
            input = sc.nextInt();
        } while (!allowedNums.contains(input));
        return input;
    }

    public static String getStringInput() {
        // Accepts any non-empty string from the user
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            System.out.print("> ");
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }



    // Printing / Ui Stuff
    public static void printTop(){
        // prints the top/beginning part of the border
        String sectionOne = centerText("/\\", BORDER_CENTER_AMOUNT);
        String sectionTwo = centerText("\\  /", BORDER_CENTER_AMOUNT);
        System.out.println(sectionOne + centerText("", BORDER_TEXT_CENTER) + sectionOne);
        System.out.println(sectionTwo + centerText("", BORDER_TEXT_CENTER) + sectionTwo);
        printFull();
    }
    public static void printBottom(){
        // prints the bottom/end part of the border
        printFull();
        String sectionOne = centerText("/  \\", BORDER_CENTER_AMOUNT);
        String sectionTwo = centerText("\\/", BORDER_CENTER_AMOUNT);
        System.out.println(sectionOne + centerText("", BORDER_TEXT_CENTER) + sectionOne);
        System.out.println(sectionTwo + centerText("", BORDER_TEXT_CENTER) + sectionTwo);
    }


    public static void printBordered(String text) {
        // Prints text with decorative borders on both sides
        delay(150);
        String borderPart = centerText(border[borderSegment][segmentSection], BORDER_CENTER_AMOUNT);
        String centeredText = centerText(text, BORDER_TEXT_CENTER);
        System.out.println(borderPart + centeredText + borderPart);
        advanceBorder();
    }

    public static void printEmptyBorder() {
        // Prints only borders without any text
        delay(150);
        String borderPart = centerText(border[borderSegment][segmentSection], BORDER_CENTER_AMOUNT);
        String centeredText = centerText("", BORDER_TEXT_CENTER);
        System.out.println(borderPart + centeredText + borderPart);
        advanceBorder();
    }

    public static void printBorderless(String text) {
        // Prints text centered with no border around it
        delay(150);
        String centeredText = centerText(text, BORDERLESS_TEXT_CENTER);
        System.out.println(centeredText);
    }

    public static void printFull() {
        // Prints a full line border with decorative patterns
        String borderPart = centerText(border[borderSegment][segmentSection], BORDER_CENTER_AMOUNT);
        String line = "=".repeat(BORDER_TEXT_CENTER);
        System.out.println(borderPart + centerText(line, BORDER_TEXT_CENTER) + borderPart);
        advanceBorder();
    }

    private static String centerText(String text, int maxWidth) {
        // Centers the given text within a specified width by padding spaces
        int padding = (maxWidth - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();
        centeredText.append(" ".repeat(Math.max(0, padding)));
        centeredText.append(text);
        while (centeredText.length() < maxWidth) {
            // fill the rest of the space to match the maxWidth
            centeredText.append(" ");
        }
        return centeredText.toString();
    }


    private static void advanceBorder() {
        // advances to next border pattern section
        segmentSection++;
        if (segmentSection > 4) {
            segmentSection = 0;
            borderSegment++;
            if (borderSegment > 3) borderSegment = 0;
        }
    }

    private static void delay(int milliseconds) {
        // creates a brief delay a smooth ui expirence
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

