package observer;

/*
Use the class you've implemented in previous assignment
 */

import java.util.Stack;

/**
 * A class that allows String to be mutable with an option to undo actions.
 *
 * @author Matan Weiss & Stav Sharon
 * @version v2.0.0 12/11/22
 */

public class UndoableStringBuilder {
    StringBuilder current;
    Stack<StringBuilder> history;

    /**
     * creates UndoableStringBuilder with the specified String
     *
     * @param str A string to initialize UndoableStringBuilder with
     */
    UndoableStringBuilder(String str) {
        if (str == null)
            str = "";
        current = new StringBuilder(str);
        history = new Stack<StringBuilder>();
    }

    /**
     * creates UndoableStringBuilder with an empty String
     */
    UndoableStringBuilder() {
        this("");
    }

    /**
     * Appending the given String to the current UndoableStringBuilder
     *
     * @param str A String to append to the current UndoableStringBuilder
     * @return An UndoableStringBuilder after the addition
     */
    public UndoableStringBuilder append(String str) {
        history.push(new StringBuilder(current.toString()));
        current = current.append(str);
        return this;
    }

    /**
     * Removing a part of the current UndoableStringBuilder
     *
     * @param start an index to the first character that we are willing to remove
     *              (inclusive)
     * @param end   an index to the last character to remove (exclusive)
     * @return An UndoableStringBuilder after the deletion
     */
    public UndoableStringBuilder delete(int start, int end) {
        StringBuilder temp = new StringBuilder(current.toString());
        try {
            current.delete(start, end);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Cannot delete with the following indexes: " + e.getMessage());
        }
        history.push(temp);
        return this;
    }

    /**
     * Adding a String to the middle of the UndoableStringBuilder
     *
     * @param offset an index to insert the String into
     * @param str    A String to insert
     * @return An UndoableStringBuilder after the insertion
     */
    public UndoableStringBuilder insert(int offset, String str) {
        StringBuilder temp = new StringBuilder(current.toString());
        try {
            current.insert(offset, str);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Cannot insert to the following indexe: " + e.getMessage());
        }
        history.push(temp);
        return this;
    }

    /**
     * Replacing a part of the UndoableStringBuilder with a String
     *
     * @param start An index to start replacing with (inclusive)
     * @param end   An index to end the replace (exclusive)
     * @param str   A String to put instead of previous indexes
     * @return An UndoableStringBuilder after the replace operation
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        StringBuilder temp = new StringBuilder(current.toString());
        try {
            current = current.replace(start, end, str);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Cannot replace with the following indexes: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Cannot replace with 'null': " + e.getMessage());
        }
        history.push(temp);
        return this;
    }

    /**
     * Reversing the UndoableStringBuilder
     *
     * @return A reversed UndoableStringBuilder
     */
    public UndoableStringBuilder reverse() {
        history.push(new StringBuilder(current.toString()));
        current = current.reverse();
        return this;
    }

    /**
     * Undoing the last operation on the UndoableStringBuilder
     *
     */
    public void undo() {
        if (!history.isEmpty()) {
            StringBuilder last = history.pop();
            current = last;
            return;
        }
        System.out.println("there is nothing to undo");
    }

    /**
     * @return A String containing the value of the UndoableStringBuilder
     */
    public String toString() {
        return current.toString();
    }

    public static void main(String[] args) {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("to be or not to be");
        System.out.println(usb);
        usb.replace(3, 5, "eat");
        System.out.println(usb);
        usb.replace(17, 19, "eat");
        System.out.println(usb);
        usb.reverse();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
        usb.undo();
    }
}
