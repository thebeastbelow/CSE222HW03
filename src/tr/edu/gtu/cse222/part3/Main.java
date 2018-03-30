package tr.edu.gtu.cse222.part3;

import tr.edu.gtu.cse222.part1.Course;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static CustomList courses;

    public static void main(String[] args) {
        CustomList item = courses = new CustomList();
        readCSVFile();
        System.out.println("s: " + item.size());
        item = item.next();
        System.out.println("1: " + item.get());
        item = item.nextInSemester();
        System.out.println("2: " + item.get());
        item = item.nextInSemester();
        System.out.println("3: " + item.get());
        item = item.nextInSemester();
        System.out.println("4: " + item.get());
        item = item.nextInSemester();
        System.out.println("5: " + item.get());
        item = item.nextInSemester();
        System.out.println("6: " + item.get());
        System.out.println("::::::");
        item = courses;
        try {
            item.remove();
        } catch (CustomList.NullItemRemoveException e) {
            e.printStackTrace();
        }
        System.out.println(item.size());
        System.out.println(courses.size());
        while ((item = item.next()) != null) {
            System.out.println(item.get());
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

        CustomList current;
        current = courses;
        // Populate courses linked list
        for (int i = 1; i < 4; i++) {
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
                current.add(new Course(semester, code, title, ECTSCredits,
                        GTUCredits, htl));
                if (current.size() > 0) {
                    current = current.next();
                }
            }
            catch (NumberFormatException e) {
                System.out.println("There is problem in the format of the " +
                        "given data.");
            } catch (CustomList.NullItemAddException e) {
                System.out.println("Null item add attempt detected.");
                e.printStackTrace();
            }
        }
    }
}
