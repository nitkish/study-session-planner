import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
        System.out.print("Type the course name: ");
        String name = scanner.nextLine().trim(); //using scanners to get the data from the user, here is name
        if (name.isEmpty()) {
            System.out.println("Course name can't be empty.");
            return;
        }
        LocalDate examDate = readExamDate(scanner);
        courses.add(new Course(name, examDate));
        System.out.println("Added course: " + name);
    }

    //private class to help with the addCourse class to get the exam date
    private static LocalDate readExamDate(Scanner scanner) {
        while (true) {
            System.out.print("Type in the exam date (YYYY-MM-DD): ");
            String text = scanner.nextLine().trim(); //scanner for exam date
            try {
                LocalDate date = LocalDate.parse(text);
                if (date.isAfter(LocalDate.now())) {
                    return date;
                }
                System.out.println("The exam date must be in the future."); //cancels out exams set in the past, because why would you study for an exam that already passed.
            } catch (DateTimeParseException e) {
                System.out.println("That isn't a valid date. Use YYYY-MM-DD, like 2026-12-15.");
            }
        }
    }
    //viewCourses shows all the course exams upcoming with sortByExamDate private method
    private static void viewCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses yet. Choose 1 to add one.");
            return;
        }
        sortByExamDate(courses);
        System.out.println("Your courses (soonest exam first):");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i));
        }
    }

    //sortByExamDate makes the exams viewable from earliest to latest.
    private static void sortByExamDate(List<Course> courses) {
        for (int i = 0; i < courses.size() - 1; i++) {
            int earliest = i;
            for (int j = i + 1; j < courses.size(); j++) {
                if (courses.get(j).getExamDate().isBefore(courses.get(earliest).getExamDate())) {
                    earliest = j;
                }
            }
            Course current = courses.get(i);
            courses.set(i, courses.get(earliest));
            courses.set(earliest, current);
        }
    }

    //prints out the schedule
    private static void generateSchedule(Scanner scanner, List<Course> courses) {
        if (courses.isEmpty()){
            System.out.println("Add at least one course first.");
            return;
        }
        double hoursPerDay = readHoursPerDay(scanner);
        System.out.println("Planning with " + hoursPerDay + " study hours per day...");
    }

    //keeps on asking the user to type a number between 0 and 24 (inclusive)
    private static double readHoursPerDay(Scanner scanner) {
        while (true) {
            System.out.print("Hours you can study each day (ex. 3 or 2.5): ");
            String text = scanner.nextLine().trim();
            try {
                double hours = Double.parseDouble(text);
                if (hours > 0 && hours <= 24) {
                    return hours;
                }
                System.out.println("Enter a number greater than 0 and at most 24.");
            } catch (NumberFormatException e) {
                System.out.println("That isn't a number, try again.");
            }
        }
    }
}