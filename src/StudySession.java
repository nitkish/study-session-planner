import java.time.LocalDate;
/**
 * StudySession creates one block of study time (which course, on which day, for how many hours)
 */
public class StudySession {
    private final Course course;
    private final LocalDate date;
    private final double hours;

    public StudySession(Course course, LocalDate date, double hours) {
        this.course = course;
        this.date = date;
        this.hours = hours;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }

    @Override
    public String toString() {
        return course.getName() + " - " + String.format("%.2f", hours) + " hrs";
    }
}