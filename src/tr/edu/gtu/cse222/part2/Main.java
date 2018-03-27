package tr.edu.gtu.cse222.part2;

import tr.edu.gtu.cse222.part1.Course;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;

public class Main {
    private static DisabledLinkedList<Course> courses;
    public static void main(String[] args) {
        courses = new DisabledLinkedList<>();
        readCSVFile();  // populate courses

        int sizeBeforeDisables = courses.size();
        Course courseBeforeItDisabled = courses.get(3);

        try {
            courses.disable(1);
            courses.disable(3);
            courses.disable(5);
        } catch (IllegalDisableAttemptException e) {
            System.out.println("error");
        }

        try {
            courses.disable(1);
        } catch (IllegalDisableAttemptException e) {
            System.out.println("You can not disable an element that is" +
                    " already disabled.");
        }

        try {
            courses.enable(2);
        } catch (IllegalEnableAttemptException e) {
            System.out.println("You can not enable an element that is not" +
                    " disabled before.");
        }

        try {
            courses.enable(1);
        } catch (IllegalEnableAttemptException e) {
            System.out.println("error");
        }

        courses.showDisabled();

        try {
            courses.get(3);
        } catch (RuntimeException e) {
            System.out.println("You can not get an element that is disabled.");
        }

        Course course = courses.get(0);

        try {
            courses.set(3, course);
        } catch (RuntimeException e) {
            System.out.println("You can not set an element that is disabled.");
        }

        if (courses.size() == sizeBeforeDisables - 2) {
            System.out.println("Disabled elements is not counted in size().");
        }

        try {
            courses.remove(3);
        } catch (RuntimeException e) {
            System.out.println("You can not remove an element " +
                    "that is disabled.");
        }

        ListIterator<Course> listIterator = courses.listIterator();
        boolean isDisabledOneFound = false;
        while (listIterator.hasNext()) {
            if (listIterator.next() == courseBeforeItDisabled) {
                isDisabledOneFound = true;
            }
        }

        if (!isDisabledOneFound) {
            System.out.println("List iterators doesn't contain " +
                    "disabled items.");
        }
    }

    /**
     * Tries to read course information from csv file. Populates
     * {@code courses} LinkedList.
     */
    private static void readCSVFile() {
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
}
