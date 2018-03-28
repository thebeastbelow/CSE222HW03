package tr.edu.gtu.cse222.part2;

import java.util.ArrayList;

/**
 * Represents list of disabled elements. Contains methods to manage locations
 * of the disabled elements in the list by shifting and unshifting related
 * elements.
 *
 * @author Enes Gonultas
 * @see DisabledLinkedList
 * @see DisabledElement
 */
public class DisabledElements<E> {
    private ArrayList<DisabledElement<E>> list;

    public DisabledElements() {
        list = new ArrayList<>();
    }

    /**
     * Adds a {@code DisabledElement} to the list.
     *
     * @param element a disabled element
     */
    public void add(DisabledElement<E> element) {
        list.add(element);
    }

    /**
     * Removes the {@code DisabledElement} which has the given index as its
     * {@code location}.
     *
     * @param index location of the disabled guy
     */
    public void remove(int index) {
        DisabledElement<E> wanted = null;
        for (DisabledElement<E> element : list) {
            if (element.getLocation() == index) {
                wanted = element;
            }
        }
        list.remove(wanted);
    }

    /**
     * Returns the size of the disabled elements list.
     *
     * @return number of the disableds
     */
    public int size() {
        return list.size();
    }

    /**
     * Checks if the {@code list} has an element which has given index as its
     * location. In other words checks if the element in the given index is
     * disabled.
     *
     * @param index index of an element
     * @return if item is disabled
     */
    public boolean checkIfDisabled(int index) {
        boolean isDisabled = false;

        for (DisabledElement<E> disabledElement : list) {
            if (disabledElement.getLocation() == index) {
                isDisabled = true;
            }
        }

        return isDisabled;
    }

    /**
     * Shifts the elements of the {@code list} after the given
     * {@code startingIndex}, including the {@code startingIndex}. i.e.
     * increases their {@code previousLocation} by one.
     *
     * @param startingIndex index to start shifting
     */
    public void shiftAfter(int startingIndex) {
        list.forEach(element -> {
            int prevLoc = element.getLocation();
            if (prevLoc >= startingIndex) {
                element.setLocation(prevLoc + 1);
            }
        });
    }

    /**
     * Unshifts the elements of the {@code list} after the given
     * {@code startingIndex}, excluding the {@code startingIndex}. i.e.
     * decreases their {@code previousLocation} by one.
     *
     * @param startingIndex index to start unshifting
     */
    public void unshiftAfter(int startingIndex) {
        list.forEach(element -> {
            int prevLoc = element.getLocation();
            if (prevLoc > startingIndex) {
                element.setLocation(prevLoc - 1);
            }
        });
    }

    /**
     * Returns the disabled element which has given index as its location.
     *
     * @param index location of the disabled element
     * @return the disabled element
     * @throws InvalidIndexException if there is no element with given index as
     * its location
     */
    public DisabledElement<E> get(int index) throws InvalidIndexException {
        DisabledElement<E> wanted = null;
        for (DisabledElement<E> disabledElement : list) {
            if (disabledElement.getLocation() == index) {
                wanted = disabledElement;
            }
        }

        if (wanted == null) {
            throw new InvalidIndexException();
        }
        return wanted;
    }
}
