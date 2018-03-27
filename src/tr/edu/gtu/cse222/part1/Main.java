package tr.edu.gtu.cse222.part1;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Course> filtered = null;

        CourseStructure cs = new CourseStructure("courses.csv");
        try {
            filtered = cs.getByCode("CSE 222");
        } catch (CourseStructure.CodeNotFoundException e) {
            e.printStackTrace();
        }
        filtered.forEach(System.out::println);

        try {
            filtered = cs.listSemesterCourses(4);
        } catch (CourseStructure.SemesterNotFoundException e) {
            e.printStackTrace();
        }
        filtered.forEach(System.out::println);

        try {
            filtered = cs.getByRange(2, 10);
        } catch (CourseStructure.EmptyRangeException e) {
            e.printStackTrace();
        }
        filtered.forEach(System.out::println);
    }
}
