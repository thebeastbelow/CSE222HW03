package tr.edu.gtu.cse222.part3.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tr.edu.gtu.cse222.part1.Course;
import tr.edu.gtu.cse222.part3.CustomList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomListTest {
    private CustomList customList;
    private Course course1, course2;

    @BeforeEach
    void init() {
        customList = new CustomList();
        course1 = new Course(1, "222", "Data Structures", 1, 1, ".");
        course2 = new Course(1, "223", "Structures Data", 1, 1, ".");
    }

    @Test
    void add() {
        try {
            customList.add(course1);
        } catch (CustomList.NullItemAddException e) {
            e.printStackTrace();
        }
        assertEquals(customList.size(), 1);
        assertEquals(customList.next().get(), course1);
    }

    @Test
    void remove() {
        try {
            customList.add(course1);
        } catch (CustomList.NullItemAddException e) {
            e.printStackTrace();
        }
        try {
            customList.remove();
        } catch (CustomList.NullItemRemoveException e) {
            e.printStackTrace();
        }
        assertEquals(customList.size(), 0);
    }

    @Test
    void next() {
        CustomList current = customList;
        try {
            current.add(course1);
            current = current.next();
            current.add(course2);
        } catch (CustomList.NullItemAddException e) {
            e.printStackTrace();
        }
        assertEquals(customList.next().get(), course1);
        customList = customList.next();
        assertEquals(customList.next().get(), course2);
        customList = customList.next();
        assertNull(customList.next());
    }

    @Test
    void nextInSemester() {
        CustomList current = customList;
        try {
            current.add(course1);
            current = current.next();
            current.add(course2);
        } catch (CustomList.NullItemAddException e) {
            e.printStackTrace();
        }
        customList = customList.next();
        assertEquals(customList.nextInSemester().get(), course2);
        customList = customList.nextInSemester();
        assertEquals(customList.nextInSemester().get(), course1);
        customList = customList.nextInSemester();
        assertEquals(customList.nextInSemester().get(), course2);
        customList = customList.nextInSemester();
        assertEquals(customList.nextInSemester().get(), course1);
    }

    @Test
    void size() {
        CustomList current = customList;
        try {
            current.add(course1);
            current = current.next();
            current.add(course2);
        } catch (CustomList.NullItemAddException e) {
            e.printStackTrace();
        }
        assertEquals(current.size(), 2);
    }

    @Test
    void get() {
        try {
            customList.add(course1);
        } catch (CustomList.NullItemAddException e) {
            e.printStackTrace();
        }
        assertEquals(customList.next().get(), course1);
    }
}
