package mcga.brainfuck;

/**
 * Created by user on 26/09/2016.
 */
public class Memory {
    int currentIndex = 0;
    final int MAX_SIZE=30000;
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
     * @param i value in a memory cell
     * @return true if i is between the values, false otherwise
     */
    boolean isValidNumber(int i) {
        return i >= 0 && i < 256;
    }

    /**
     * Check if the parameter i is -1 or 1
     * @param i parameter of an instruction
     * @return true if i is -1 or 1, false otherwise
     */
    boolean isValidInput(int i) {
        return (i == -1 || i == 1);
    }

    /**
     * Check if the cell exists int the memory
     * @param i index of the current cell
     * @return true if it exists, false otherwise
     */
    boolean isValidIndex(int i) { return currentIndex+i>=0 && currentIndex+i<=MAX_SIZE;}

    /**
     * Add value i to the current cell
     * @param i value to add in the current cell
     * @throws InvalidValueException
     */
    void addCurrentCellValue(int i) throws InvalidValueException {
        int val = getCurrentCellValue();
        if (isValidInput(i) && isValidNumber(val + i)) {
            memoire[currentIndex]= val + i;
//            System.out.println("Memory pointer : " + currentIndex + " la valeur vaut : " + memoire.get(currentIndex));
        } else {
            throw new InvalidValueException();
        }

    }

    /**
     * Change the index of the current cell
     * @param i value to add to the current index
     * @throws MyIndexOutOfBoundsException
     * @throws InvalidValueException
     */
    void changeCurrentIndex(int i) throws MyIndexOutOfBoundsException, InvalidValueException {
        int val = currentIndex;
        if (!isValidInput(i)) {
            throw new InvalidValueException();
        }
        if(!isValidIndex(i)){
            throw new MyIndexOutOfBoundsException();
        }
        currentIndex += i;
    }

    /**
     * @return String version of the memory
     */
    @Override
    public String toString() {
        return memoire.toString();
    }

    /**
     * @return value of the current cell
     */
    public Integer getCurrentCellValue() {
        return memoire[currentIndex];
    }

    /**
     * Put the value of the current index to zero
     */
    public void clearCurrentCell() {
        memoire[currentIndex]=0;
    }
}