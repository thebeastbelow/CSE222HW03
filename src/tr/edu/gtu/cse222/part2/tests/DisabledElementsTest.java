package tr.edu.gtu.cse222.part2.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tr.edu.gtu.cse222.part2.DisabledElement;
import tr.edu.gtu.cse222.part2.DisabledElements;
import tr.edu.gtu.cse222.part2.InvalidIndexException;

import static org.junit.jupiter.api.Assertions.*;

class DisabledElementsTest {
    private DisabledElement<Integer> disabledInteger;
    private DisabledElement<Integer> disabledInteger2;
    private DisabledElements<Integer> list;

    @BeforeEach
    void init() {
        disabledInteger = new DisabledElement<>(0, 5);
        disabledInteger2 = new DisabledElement<>(1, 78);
        list = new DisabledElements<>();
    }

    // Tests add method
    @Test
    void add() {
        list.add(disabledInteger);
        try {
            assertEquals(list.get(0), disabledInteger);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }

    // Tests remove method
    @Test
    void remove() {
        list.add(disabledInteger);
        list.remove(0);
        assertThrows(InvalidIndexException.class, () -> {
            list.get(0);
        });
    }

    // Tests size method
    @Test
    void size() {
        assertEquals(0, list.size());
        list.add(disabledInteger);
        assertEquals(1, list.size());
        list.add(disabledInteger2);
        assertEquals(2, list.size());
        list.remove(0);
        assertEquals(1, list.size());
        list.remove(1);
        assertEquals(0, list.size());
    }

    // Tests checkIfDisabled method
    @Test
    void checkIfDisabled() {
        list.add(disabledInteger2);
        assertTrue(list.checkIfDisabled(1));
    }

    // Tests shiftAfter method
    @Test
    void shiftAfter() {
        list.add(disabledInteger);
        assertTrue(list.checkIfDisabled(0));
        list.shiftAfter(0);
        assertFalse(list.checkIfDisabled(0));
        assertTrue(list.checkIfDisabled(1));
    }

    // Tests unshiftAfter method
    @Test
    void unshiftAfter() {
        list.add(disabledInteger2);
        assertTrue(list.checkIfDisabled(1));
        list.unshiftAfter(0);
        assertTrue(list.checkIfDisabled(0));
        assertFalse(list.checkIfDisabled(1));
    }

    // Tests get method
    @Test
    void get() {
        list.add(disabledInteger);
        try {
            assertEquals(disabledInteger, list.get(0));
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
        assertThrows(InvalidIndexException.class, () -> {
            list.get(1);
        });
    }
}
