# Study Session Planner

A Java command-line program that builds a day-by-day study schedule from my exam dates and the hours I have available each day.

## Project Goal

I spend too much time playing video games and not enough time studying, mostly because I never plan my study time ahead. This program lets me enter my courses, exam dates, and daily study hours, then generates a schedule so I know exactly what to work on each day.

## MVP Scope

Version 1.0 includes:

- Add a course with its exam date (with date validation)
- Enter the hours available to study per day
- Generate a schedule that gives more time to closer exams (varies by day)
- View courses sorted by exam date
- Print the schedule in the terminal
- Delete a course (only one exam at a time)

## Future Plans (V2.0+)

- Save and load courses from a file
- Tests that are closer for a course / difficulty of course adjusts study time
- Mark study sessions as completed
- Edit courses
- Weekly stats on hours studied vs. planned

## Tech Stack

- **Language:** Java (command-line program)
- **Libraries:** Java standard libraries only, consisting of `java.time.LocalDate`, and `java.util.ArrayList`, `java.util.Scanner`.
- **Data storage:** In memory (an `ArrayList` of `Course` objects) for the MVP
- **External data sources:** All data is entered by the user
- **Tools:** Git, GitHub, and IntelliJ

## Documentation

- [Eisenhower Matrix](docs/eisenhower-matrix.pdf)
- [System Diagram](docs/system-diagram.png) (source file: `docs/system-diagram.drawio` [in the docs folder])
