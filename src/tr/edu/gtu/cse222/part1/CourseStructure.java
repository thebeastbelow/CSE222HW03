package tr.edu.gtu.cse222.part1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents course structure. Reads course information from csv file, keeps
 * records in a Java LinkedList. Users can get a list of courses by course
 * code, get a list of courses on given semester and get a sublist of course
 * list by giving a range.
 *
 * @author Enes Gonultas
 * @see LinkedList
 */
public class CourseStructure {
    private LinkedList<Course> courses;
    private String fileName;

    public CourseStructure(String fileName) {
        courses = new LinkedList<>();
        this.fileName = fileName;
        readCSVFile();
    }

    /**
     * Returns a list of courses with given code.
     *
     * @param code course code to filter
     * @return a filtered list of courses
     * @throws CodeNotFoundException if code is not found
     */
    public LinkedList<Course> getByCode(String code)
            throws CodeNotFoundException {
        LinkedList<Course> subList = new LinkedList<>();

        courses.forEach((course) -> {
            if (code.equals(course.getCode())) {
                subList.add(course);
            }
        });

        if (subList.size() == 0) {
            throw new CodeNotFoundException();
        }

        return subList;
    }

    /**
     * Returns a list of courses with given semester value.
     *
     * @param semester semester to filter
     * @return a filtered list of courses
     * @throws SemesterNotFoundException if semester is not found
     */
    public LinkedList<Course> listSemesterCourses(int semester)
            throws SemesterNotFoundException {
        LinkedList<Course> subList = new LinkedList<>();

        courses.forEach((course) -> {
            if (course.getSemester() == semester) {
                subList.add(course);
            }
        });

        if (subList.size() == 0) {
            throw new SemesterNotFoundException();
        }

        return subList;
    }

    /**
     * Returns a sublist of courses starting from (and including)
     * {@code startIndex} up to (but excluding) lastIndex.
     *
     * @param startIndex start index of sublist
     * @param lastIndex end index of sublist
     * @return a sublist of courses
     * @throws IndexOutOfBoundsException if indices out of bounds
     * @throws EmptyRangeException if {@code startIndex == lastIndex}
     */
    public LinkedList<Course> getByRange(int startIndex, int lastIndex)
            throws EmptyRangeException, IndexOutOfBoundsException {
        LinkedList<Course> subList = new LinkedList<>();

        if (startIndex < 0 || lastIndex > courses.size()) {
            throw new IndexOutOfBoundsException();
        }
        else if (startIndex == lastIndex) {
            throw new EmptyRangeException();
        }

        for (int i = startIndex; i < lastIndex; i++) {
            subList.add(courses.get(i));
        }

        return subList;
    }

    /**
     * Tries to read course information from csv file. Populates
     * {@code courses} LinkedList.
     */
    private void readCSVFile() {
        // try to read records from csv file
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileName),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.print("CSV file read error.");
            e.printStackTrace();
            System.exit(1);
        }

        // Populate courses linked list
        for (int i = 1; i < lines.size(); i++) {
            String[] data = lines.get(i).split(";");

            try {
                int semester;
                semester = Integer.parseInt(data[0]);
                String code;
                code = data[1];
                String title = data[2];
                int ECTSCredits = Integer.parseInt(data[3]);
                int GTUCredits = Integer.parseInt(data[4]);
                String htl = data[5];
                courses.add(new Course(semester, code, title, ECTSCredits,
                        GTUCredits, htl));
            }
            catch (NumberFormatException e) {
                System.out.println("There is problem in the format of the " +
                        "given data.");
            }
        }
    }

    public class CodeNotFoundException extends Exception {}
    public class SemesterNotFoundException extends Exception {}
    public class EmptyRangeException extends Exception {}
}
