package tr.edu.gtu.cse222.part1.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tr.edu.gtu.cse222.part1.Course;
import tr.edu.gtu.cse222.part1.CourseStructure;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CourseStructureTest {

    private LinkedList<Course> filtered;
    CourseStructure cs;

    @BeforeEach
    void init() {
        cs = new CourseStructure("courses.csv");
        filtered = null;
    }

    // Tests getByCode method
    @Test
    void getByCode() {
        try {
            filtered = cs.getByCode("CSE 102");
        } catch (CourseStructure.CodeNotFoundException e) {
            System.out.println("error");
            System.exit(1);
        }
        assertEquals(filtered.get(0).getCode(), "CSE 102");
        assertThrows(CourseStructure.CodeNotFoundException.class,
                () -> cs.getByCode("XYZ 111"));
    }

    // Tests listSemesterCourses method
    @Test
    void listSemesterCourses() {
        String[] secondSemesterCourseCodes = {
                "XXX XXX",
                "CSE 102",
                "CSE 108",
                "MATH 102",
                "PHYS 122",
                "PHYS 152",
                "SSTR 102",
                "TUR 102"
        };
        try {
            filtered = cs.listSemesterCourses(2);
        } catch (CourseStructure.SemesterNotFoundException e) {
            System.out.println("error");
            System.exit(1);
        }
        // check if all courses in the semester are returned
        for (String secondSemesterCourseCode : secondSemesterCourseCodes) {
            boolean found = false;
            for (Course course : filtered) {
                if (secondSemesterCourseCode.equals(course.getCode())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
        // check if no other courses returned
        assertEquals(secondSemesterCourseCodes.length, filtered.size());
        // check the corresponding exception
        assertThrows(CourseStructure.SemesterNotFoundException.class,
                () -> cs.listSemesterCourses(9));
    }

    // Tests getByRange method
    @Test
    void getByRange() {
        String[] coursesFromThreeToNine = {
                "MATH 101",
                "PHYS 121",
                "PHYS 151",
                "SSTR 101",
                "TUR 101",
                "XXX XXX"
        };
        try {
            filtered = cs.getByRange(3, 9);
        } catch (CourseStructure.EmptyRangeException e) {
            System.out.println("error");
            System.exit(1);
        }
        // check if all courses in the range are returned
        for (String courseFromThreeToNine : coursesFromThreeToNine) {
            boolean found = false;
            for (Course course : filtered) {
                if (courseFromThreeToNine.equals(course.getCode())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
        // check if no other courses returned
        assertEquals(coursesFromThreeToNine.length, filtered.size());
        // check the corresponding exceptions
        assertThrows(CourseStructure.EmptyRangeException.class,
                () -> cs.getByRange(5, 5));
        assertThrows(IndexOutOfBoundsException.class,
                () -> cs.getByRange(-5, 5));
        assertThrows(IndexOutOfBoundsException.class,
                () -> cs.getByRange(-5, 1000));
    }
}
