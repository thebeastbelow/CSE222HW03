package tr.edu.gtu.cse222.part2;

/**
 * Represents a disabled element. Holds a disabled element itself with its
 * previous location.
 *
 * @author Enes Gonultas
 * @param <E> type of the element
 * @see DisabledLinkedList
 * @see DisabledElements
 */
public class DisabledElement<E> {
    private int location;
    private E element;

    public DisabledElement(int prevLoc, E element) {
        this.location = prevLoc;
        this.element = element;
    }

    /**
     * Gets location of the disabled element.
     *
     * @return location of the disabled element
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets location of the disabled element.
     *
     * @param location new location value
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Gets the actual element.
     *
     * @return the actual element
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets the actual element.
     *
     * @param element new element
     */
    public void setElement(E element) {
        this.element = element;
    }
}
