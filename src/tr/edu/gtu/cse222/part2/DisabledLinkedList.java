package tr.edu.gtu.cse222.part2;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Defines a new type of LinkedList that supports element disabling. Disabled
 * elements can not be get'ed, set'ed, remove'ed; they are not included to the
 * size and iterator. A list iterator starting from them cannot be created.
 *
 * @param <E> type of the elements of disabled linked list
 */
public class DisabledLinkedList<E> extends LinkedList<E> {
    private DisabledElements<E> disableds;

    DisabledLinkedList() {
        disableds = new DisabledElements<>();
    }

    /**
     * Disables an element in the list.
     *
     * @param index index of the element to be disabled
     * @throws IllegalDisableAttemptException if item is already disabled
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public void disable(int index) throws IllegalDisableAttemptException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (disableds.checkIfDisabled(index)) {
            throw new IllegalDisableAttemptException();
        }

        // create a new disabled element
        DisabledElement<E> disabled =
                new DisabledElement<>(index, this.get(index));
        // add it to disableds list
        disableds.add(disabled);
        // replace the actual element with null
        super.set(index, null);
    }

    /**
     * Enables an element in the list back.
     *
     * @param index of the element to be enabled
     * @throws IllegalEnableAttemptException if item is not disabled
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public void enable(int index) throws IllegalEnableAttemptException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (!disableds.checkIfDisabled(index)) {
            throw new IllegalEnableAttemptException();
        }

        try {
            super.set(index, disableds.get(index).getElement());
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
        disableds.remove(index);
    }

    /**
     * Prints disabled elements on the screen.
     */
    public void showDisabled() {
        for (int i = 0; i < this.size(); i++) {
            if (disableds.checkIfDisabled(i)) {
                try {
                    DisabledElement<E> disabled = disableds.get(i);
                    System.out.println("Disabled Element Index: "
                            + disabled.getLocation());
                    System.out.println("Disabled Element: \n"
                            + disabled.getElement());
                } catch (InvalidIndexException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Returns if the element in the given index is disabled.
     *
     * @param index index of the element
     * @return if the element is disabled
     */
    public boolean isDisabled(int index) {
        return disableds.checkIfDisabled(index);
    }

    // updates disabled list of clones
    private void setDisableds(DisabledElements<E> disableds) {
        this.disableds = disableds;
    }

    @Override
    public void add(int index, E element) {
        // shift the disableds
        disableds.shiftAfter(index);
        super.add(index, element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (int i = 0; i < c.size(); i++) {
            // shift the disableds
            disableds.shiftAfter(index);
        }
        return super.addAll(index, c);
    }

    @Override
    public void addFirst(E e) {
        // shift the disables
        disableds.shiftAfter(0);
        super.addFirst(e);
    }

    @Override
    public void clear() {
        // clear the disableds
        disableds = new DisabledElements<>();
        super.clear();
    }

    @Override
    public Object clone() {
        Object obj = super.clone();
        if (!(obj instanceof DisabledLinkedList)) {
            throw new RuntimeException();
        }
        DisabledLinkedList<E> clone = (DisabledLinkedList<E>) obj;
        // clone disableds
        clone.setDisableds(disableds);
        return clone;
    }

    // returns super's descending iterator, this is a helper for
    // descendingIterator method
    private Iterator<E> superDescendingIterator() {
        return super.descendingIterator();
    }

    @Override
    public Iterator<E> descendingIterator() {
        DisabledLinkedList<E> clone = (DisabledLinkedList<E>) super.clone();
        // remove nulls of disableds
        while (clone.remove(null));
        return clone.superDescendingIterator();
    }

    @Override
    public E get(int index) {
        // check if the element in given index is disabled
        if (disableds.checkIfDisabled(index)) {
            throw new RuntimeException("Illegal attempt to get a " +
                    "disabled item");
        }
        return super.get(index);
    }

    // returns super's list iterator, this is a helper for listIterator method
    private ListIterator<E> superListIterator() {
        return super.listIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        DisabledLinkedList<E> clone = (DisabledLinkedList<E>) super.clone();
        // remove nulls of disableds
        while (clone.remove(null));
        return clone.superListIterator();
    }

    // returns super's list iterator, this is a helper for listIterator method
    private ListIterator<E> superListIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        // check if the element in given index is disabled
        if (disableds.checkIfDisabled(index)) {
            throw new RuntimeException("Illegal attempt to get a list " +
                    "iterator starting from a disabled item");
        }
        DisabledLinkedList<E> clone = (DisabledLinkedList<E>) super.clone();
        // remove nulls of disableds
        while (clone.remove(null));
        return clone.superListIterator(index);
    }

    @Override
    public boolean offerFirst(E e) {
        // shift the disableds
        disableds.shiftAfter(0);
        return super.offerFirst(e);
    }

    @Override
    public E poll() {
        // unshift the disableds
        disableds.unshiftAfter(0);
        return super.poll();
    }

    @Override
    public E pollFirst() {
        // unshift the disableds
        disableds.unshiftAfter(0);
        return super.pollFirst();
    }

    @Override
    public E remove() {
        // unshift the disableds
        disableds.unshiftAfter(0);
        return super.remove();
    }

    @Override
    public E remove(int index) {
        // check if the element in given index is disabled
        if (disableds.checkIfDisabled(index)) {
            throw new RuntimeException("Illegal attempt to remove a " +
                    "disabled item");
        }
        // unshift the disableds
        disableds.unshiftAfter(index);
        return super.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        // unshift the disableds
        disableds.unshiftAfter(this.indexOf(o));
        return super.remove(o);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        // unshift the disableds
        for (int i = fromIndex; i < toIndex; i++) {
            disableds.unshiftAfter(fromIndex);
        }
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public E removeFirst() {
        // unshift the disableds
        disableds.unshiftAfter(0);
        return super.removeFirst();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        disableds = new DisabledElements<>();
        return super.removeAll(c);
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        // unshift the disableds
        disableds.unshiftAfter(this.indexOf(o));
        return super.removeFirstOccurrence(o);
    }

    @Override
    public E pop() {
        // unshift the disableds
        disableds.unshiftAfter(0);
        return super.pop();
    }

    @Override
    public void push(E e) {
        // shift the disableds
        disableds.shiftAfter(0);
        super.push(e);
    }

    @Override
    public E set(int index, E element) {
        if (disableds.checkIfDisabled(index)) {
            throw new RuntimeException("Illegal attempt to set a " +
                    "disabled item");
        }
        return super.set(index, element);
    }

    @Override
    public int size() {
        return super.size() - disableds.size();
    }
}
