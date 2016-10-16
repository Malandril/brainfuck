package mcga.brainfuck;

import java.util.StringJoiner;

/**
 * Created by user on 26/09/2016.
 */
public class Memory {
    private final int MAX_SIZE = 30000;
    private int currentIndex = 0;
    private int[] memoire;

    /**
     * Constructor of the class Memory
     */
    public Memory() {
        this.memoire = new int[MAX_SIZE];
    }

    /**
     * @return currentIndex
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Check if the parameter i is between 0 and 255
     *
     * @param i value in a memory cell
     * @return true if i is between the values, false otherwise
     */
    private boolean isValidNumber(int i) {
        return i >= 0 && i < 256;
    }


    /**
     * Check if the cell exists int the memory
     *
     * @param i index of the current cell
     * @return true if it exists, false otherwise
     */
    private boolean isValidIndex(int i) {
        return currentIndex + i >= 0 && currentIndex + i <= MAX_SIZE;
    }

    /**
     * Add value i to the current cell
     *
     * @param i value to add in the current cell
     * @throws InvalidValueException
     */
    void addCurrentCellValue(int i) throws InvalidValueException {
        int val = getCurrentCellValue();
        if (isValidNumber(val + i)) {
            memoire[currentIndex] = val + i;
        } else {
            throw new InvalidValueException(val + " at index: " + currentIndex);
        }

    }

    /**
     * Change the index of the current cell
     *
     * @param i value to add to the current index
     * @throws MyIndexOutOfBoundsException
     * @throws InvalidValueException
     */
    void changeCurrentIndex(int i) throws IndexOutOfBoundsException, InvalidValueException {
        int val = currentIndex;
        if (!isValidIndex(i)) {
            throw new IndexOutOfBoundsException(val + " index must be between " + 0 + " and " + MAX_SIZE);
        } else {
            currentIndex += i;
        }
    }

    /**
     * @return String version of the memory
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
     * @return value of the current cell
     */
    public int getCurrentCellValue() {
        return memoire[currentIndex];
    }

    /**
     * Put the value of the current index to zero
     */
    public void clearCurrentCell() {
        memoire[currentIndex] = 0;
    }
}