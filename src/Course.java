import java.time.LocalDate;

/**
 * Creates a course or courses the student is studying for with it's test date.
 */
public class Course {
    private final String name;
    private final LocalDate examDate;

    public Course(String name, LocalDate examDate) {
        this.name = name;
        this.examDate = examDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    @Override
    public String toString() {
        return name + " (exam: " + examDate + ")";
    }
}