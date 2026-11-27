import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * THIS is the main logic class for my project
 * It builds a schedule from a list of courses' exams that the user provided.
 * Courses with closer exams get a bigger share of each day's hours.
 */
public class Planner {

    // This plans every day from startDate up to (but not including) the last exam day.
    public List<StudySession> generate(List<Course> courses, double hoursPerDay, LocalDate startDate) {
        List<StudySession> sessions = new ArrayList<>();
        LocalDate lastExam = findLastExamDate(courses);
        for (LocalDate day = startDate; day.isBefore(lastExam); day = day.plusDays(1)) {
            sessions.addAll(planOneDay(courses, hoursPerDay, day));
        }
        return sessions;
    }

    private LocalDate findLastExamDate(List<Course> courses) {
        LocalDate last = courses.get(0).getExamDate();
        for (Course course : courses) {
            if (course.getExamDate().isAfter(last)) {
                last = course.getExamDate();
            }
        }
        return last;
    }

    //only courses who have exams upcoming need to study today
    private List<StudySession> planOneDay(List<Course> courses, double hoursPerDay, LocalDate day) {
        List<Course> active = new ArrayList<>();
        double totalWeight = 0;
        for (Course course : courses){
            if (day.isBefore(course.getExamDate())){
                active.add(course);
                totalWeight += weightFor(course, day);
            }
        }

        // Each course gets its share of the day rounded down to a quarter hour
        double[] hours = new double[active.size()];
        double allocated = 0;
        int closestIndex = 0;
        for (int i = 0; i < active.size(); i++){
            double weight = weightFor(active.get(i), day);
            hours[i] = Math.floor(hoursPerDay * weight/totalWeight * 4) / 4.0;
            allocated += hours[i];

            if (weight > weightFor(active.get(closestIndex), day)){
                closestIndex = i;
            }
        }
        // Rounding down leaves a few spare minutes. Give them to the closest exam so the day's total for the student
        hours[closestIndex] += hoursPerDay - allocated;
        List<StudySession> daySessions = new ArrayList<>();
        for (int i = 0; i < active.size(); i++) {
            if (hours[i] > 0.001) {
                daySessions.add(new StudySession(active.get(i), day, hours[i]));
            }
        }
        return daySessions;
    }


    //Does the formula 1 / daysLeft. Basically an exam 1 day away is weighted 5x an exam 5 days away.
    private double weightFor(Course course, LocalDate day) {
        int daysLeft = (int) ChronoUnit.DAYS.between(day, course.getExamDate()); //ChronoUnit checks how far away the day is
        return 1.0 / daysLeft;
    }
}