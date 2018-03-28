package tr.edu.gtu.cse222.part2.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tr.edu.gtu.cse222.part1.Course;
import tr.edu.gtu.cse222.part2.DisabledLinkedList;
import tr.edu.gtu.cse222.part2.IllegalDisableAttemptException;
import tr.edu.gtu.cse222.part2.IllegalEnableAttemptException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class DisabledLinkedListTest {
    private DisabledLinkedList<Course> courses = new DisabledLinkedList<>();

    // Read information from csv file for tests
    @BeforeEach
    void init() {
        // try to read records from csv file
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("courses.csv"),
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

    // Tests disable method
    @Test
    void disable() {
        try {
            courses.disable(0);
        } catch (IllegalDisableAttemptException e) {
            e.printStackTrace();
        }
        assertTrue(courses.isDisabled(0));
        assertThrows(IllegalDisableAttemptException.class, () -> {
           courses.disable(0);
        });
    }

    // Tests enabled method
    @Test
    void enable() {
        assertThrows(IllegalEnableAttemptException.class, () -> {
            courses.enable(0);
        });
        try {
            courses.disable(0);
        } catch (IllegalDisableAttemptException e) {
            e.printStackTrace();
        }
        assertTrue(courses.isDisabled(0));
        try {
            courses.enable(0);
        } catch (IllegalEnableAttemptException e) {
            e.printStackTrace();
        }
        assertFalse(courses.isDisabled(0));
    }

    // Tests get method
    @Test
    void get() {
        Course course = courses.get(0);
        try {
            courses.disable(0);
        } catch (IllegalDisableAttemptException e) {
            e.printStackTrace();
        }
        assertTrue(courses.isDisabled(0));
        assertThrows(RuntimeException.class, () -> {
            courses.get(0);
        });
        try {
            courses.enable(0);
        } catch (IllegalEnableAttemptException e) {
            e.printStackTrace();
        }
        assertFalse(courses.isDisabled(0));
        assertEquals(course, courses.get(0));
    }

    // Tests listIterator method
    @Test
    void listIterator() {
        Course course = courses.get(0);
        boolean foundDisabledItem = false;
        ListIterator<Course> listIterator = courses.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next() == course) {
                foundDisabledItem = true;
            }
        }
        assertTrue(foundDisabledItem);
        foundDisabledItem = false;
        try {
            courses.disable(0);
        } catch (IllegalDisableAttemptException e) {
            e.printStackTrace();
        }
        listIterator = courses.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next() == course) {
                foundDisabledItem = true;
            }
        }
        assertFalse(foundDisabledItem);
    }

    // Tests remove method
    @Test
    void remove() {
        Course course = courses.get(0);
        try {
            courses.disable(0);
        } catch (IllegalDisableAttemptException e) {
            e.printStackTrace();
        }
        assertThrows(RuntimeException.class, () -> {
            courses.remove(0);
        });
        try {
            courses.enable(0);
        } catch (IllegalEnableAttemptException e) {
            e.printStackTrace();
        }
        courses.remove(0);
        assertNotEquals(course, courses.get(0));
    }

    // Tests set method
    @Test
    void set() {
        Course course = courses.get(1);
        try {
            courses.disable(0);
        } catch (IllegalDisableAttemptException e) {
            e.printStackTrace();
        }
        assertThrows(RuntimeException.class, () -> {
            courses.set(0, course);
        });
        try {
            courses.enable(0);
        } catch (IllegalEnableAttemptException e) {
            e.printStackTrace();
        }
        courses.set(0, course);
        assertEquals(courses.get(0), courses.get(1));
    }

    // Tests size method
    @Test
    void size() {
        int size = courses.size();
        try {
            courses.disable(0);
            courses.disable(1);
        } catch (IllegalDisableAttemptException e) {
            e.printStackTrace();
        }
        assertEquals(courses.size(), size - 2);
        try {
            courses.enable(0);
            courses.enable(1);
        } catch (IllegalEnableAttemptException e) {
            e.printStackTrace();
        }
        assertEquals(courses.size(), size);
    }
}
