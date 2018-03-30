package tr.edu.gtu.cse222.part3;

import tr.edu.gtu.cse222.part1.Course;

/**
 * Defines a custom linked list for courses. This linked list structure has
 * a new type of link between courses that allows user to access next course
 * in the same semester.
 *
 * @author Enes Gonultas
 * @see Course
 */
public class CustomList {
    private Course course;
    private CustomList next;
    private CustomList nextInSemester;
    private CustomList head;

    public CustomList() {
        course = null;
        next = null;
        nextInSemester = this;
        head = this;
    }

    private CustomList(Course course) {
        this.course = course;
        next = null;
        nextInSemester = this;
    }

    /**
     * Adds a new item to the item.
     *
     * @param newItem new item to be added after the actual item
     * @throws NullItemAddException  if user tries to add a null item
     */
    public void add(Course newItem) throws NullItemAddException {
        // check if new item is null
        if (newItem == null) {
            throw new NullItemAddException();
        }
        // add new item
        next = new CustomList(newItem);
        // share the head with your next, sharing is caring
        next.head = this.head;
        // update semester chain
        next.updateSemesterChain();
    }

    /**
     * Removes the item after this item from list and returns it.
     *
     * @return the removed item
     * @throws NullItemRemoveException if user tries to remove a null item
     */
    public Course remove() throws NullItemRemoveException {
        // check if next is null
        if (next == null) {
            throw new NullItemRemoveException();
        }
        // backup
        CustomList removed = this.next;
        // delete
        next = next.next;
        // update semester chain
        if (next != null)
            next.nextInSemester.updateSemesterChain();

        return removed.course;
    }

    /**
     * Gets next element in the list.
     *
     * @return next element in the list, or null
     */
    public CustomList next() {
        return next;
    }

    /**
     * Gets next element in the same semester.
     *
     * @return next element in the same semester
     */
    public CustomList nextInSemester() {
        return nextInSemester;
    }

    /**
     * Returns size of the list.
     *
     * @return size of the list
     */
    public int size() {
        CustomList item = head;
        int size = 0; // head itself is not counted

        while ((item = item.next) != null) {
            size += 1;
        }

        return size;
    }

    /**
     * Returns the course inside the item.
     *
     * @return the course inside the item
     */
    public Course get() {
        return course;
    }

    // Updates semester chain, courses in the same semester will be linked
    private void updateSemesterChain() {
        // iterate over the list starting from this.next
        CustomList item = this.next;
        CustomList lastOneInTheSameSemester = null;
        while (item != this) {
            // if reached the end, continue from the head
            if (item == null) {
                item = this.head.next;
                if (item == this) break;
            }
            if (item.course.getSemester() == this.course.getSemester()) {
                // find the course right before "this" that is in the same
                // semester, and assign it to this.nextInSemester
                if (this.nextInSemester == this) {
                    this.nextInSemester = item;
                }
                // also keep track of the last one in the same semester
                lastOneInTheSameSemester = item;
            }
            item = item.next;
        }
        if (lastOneInTheSameSemester != null) {
            // if last one is not null then change its nextInSemester as well
            lastOneInTheSameSemester.nextInSemester = this;
        }
    }

    public class NullItemAddException extends Exception {}
    public class NullItemRemoveException extends Exception {}
}
