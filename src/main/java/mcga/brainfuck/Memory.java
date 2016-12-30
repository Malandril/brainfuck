package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringJoiner;

/**
 * Creates the memory and contains all the methods used to deal with it.
 *
 * @author Team Make Coding Great Again
 */
public class Memory {
    public static final int MIN_CELL_VALUE = 0;
    public static final int MAX_CELL_VALUE = 255;
    public static final int MAX_SIZE = 30000;
    public static final String NOT_ENOUGH_MESSAGE = "Pas assez de cases memoire pour appeler la fonction";
    public static final int NOT_ENOUGH_CODE = 18;
    private int currentIndex = 0;
    private int[] memoire;
    private Deque<Procedure> currentProcedure = new ArrayDeque<>();

    /**
     * Constructor of the class Memory.
     */
    public Memory() {
        this.memoire = new int[MAX_SIZE];
    }

    /**
     * Gets the index of the current cell.
     *
     * @return currentIndex index of the current cell.
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int i) throws MyIndexOutOfBoundsException {
        if (isValidIndex(i)) {
            currentIndex = i;
        } else {
            throw new MyIndexOutOfBoundsException(i + " index must be between " + 0 + " and " + MAX_SIZE);
        }

    }

    /**
     * Checks if the parameter i is between 0 and 255.
     *
     * @param i value in a memory cell.
     * @return true if i is between the values, false otherwise.
     */
    private boolean isValidNumber(int i) {
        return i >= MIN_CELL_VALUE && i <= MAX_CELL_VALUE;
    }

    /**
     * Checks if the cell exists int the memory.
     *
     * @param i index of the current cell.
     * @return true if it exists, false otherwise.
     */
    private boolean isValidIndex(int i) {
        if (currentProcedure.isEmpty())
            return i >= 0 && i < MAX_SIZE;
        else
            return i >= currentProcedure.peek().startIndex && i <= currentProcedure.peek().endIndex;
    }

    /**
     * Adds i to the value of the current cell.
     *
     * @param i value to add in the current cell.
     * @throws InvalidValueException
     */
    public void addCurrentCellValue(int i) throws InvalidValueException {
        int val = getCurrentCellValue();
        if (isValidNumber(val + i)) {
            memoire[currentIndex] = val + i;
        } else {
            throw new InvalidValueException(val + i + " at index: " + currentIndex);
        }

    }

    /**
     * Changes the index of the current cell.
     *
     * @param i value to add to the current index.
     * @throws IndexOutOfBoundsException
     */
    public void changeCurrentIndex(int i) throws MyIndexOutOfBoundsException {
        int val = currentIndex;
        if (!isValidIndex(val + i)) {
            throw new MyIndexOutOfBoundsException(val + i + " index must be between " + 0 + " and " + MAX_SIZE);
        } else {
            currentIndex += i;
        }
    }

    /**
     * Returns a printable representation of the memory.
     *
     * @return String version of the memory.
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" , ");
        for (int i = 0; i < memoire.length; i++) {
            if (memoire[i] != 0) {
                joiner.add("C" + i + ": " + memoire[i]);
            }
        }
        return joiner.toString();
    }

    /**
     * Gets the current cell value.
     *
     * @return value of the current cell.
     */
    public int getCurrentCellValue() {
        return memoire[currentIndex];
    }

    /**
     * Sets the value of the current index to zero.
     */
    public void clearCurrentCell() {
        memoire[currentIndex] = 0;
    }



    public Procedure peekProcedure() {
        return currentProcedure.peek();
    }

    public void pushProcedure(Procedure procedure) {
        currentProcedure.push(procedure);
    }

    public Procedure popProcedure() {
        return currentProcedure.pop();
    }


    public boolean isProcedureStackEmpty() {
        return currentProcedure.isEmpty();
    }
}