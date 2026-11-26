import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The menu for the study session planner which has all of the core functions.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        boolean running = true;

        System.out.println("Welcome to the Study Session Planner!");

        try {
            while (running) {
                printMenu();
                String choice = scanner.nextLine().trim();
                if (choice.equals("1")) {
                    addCourse(scanner, courses);
                } else if (choice.equals("2")) {
                    viewCourses(courses);
                } else if (choice.equals("3")) {
                    generateSchedule(scanner, courses);
                } else if (choice.equals("4")) {
                    running = false;
                    System.out.println("Goodbye, good luck on your exams!");
                } else {
                    System.out.println("Please enter 1, 2, 3, or 4.");
                }

            }
        } catch (NoSuchElementException e) {

            System.out.println("");
            System.out.println("Input ended. Goodbye!");
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add course");
        System.out.println("2. View courses");
        System.out.println("3. Generate schedule");
        System.out.println("4. Quit");
        System.out.print("Choose an option: ");
    }

    //private methods for the functions, they haven't been made yet.
    private static void addCourse(Scanner scanner, List<Course> courses) {
        System.out.println("ADD NOT BUILT");
    }

    private static void viewCourses(List<Course> courses) {
        System.out.println("VIEW NOT BUILT");
    }

    private static void generateSchedule(Scanner scanner, List<Course> courses) {
        System.out.println("GENERATE NOT BUILT");
    }
}