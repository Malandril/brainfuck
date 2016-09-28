package mcga.brainfuck;

/**
 * Created by user on 26/09/2016.
 */
public class Memory {
    private java.util.List<Integer> memoire;
    int currentCellPointer = 0;

    public Memory() {
        this.memoire = new MyLinkedList<>();
        this.memoire.add(0);
    }

    public int getCurrentCellPointer() {
        return currentCellPointer;
    }

    boolean isValidNumber(int i) {
        return i >= 0 && i < 256;
    }

    void addCurrentCellValue(Integer i) {
        int val = memoire.get(currentCellPointer);
        if (val + i >= 0 && val + i < 256) {
            memoire.set(currentCellPointer, val + i);
            System.out.println("Memory pointer : " + currentCellPointer + " la valeur vaut : " + val);
        }

    }

    void changeCurrentPointerValue(Integer i) throws IndexOutOfBoundsException{
        int val = currentCellPointer;
        if (val + i >= memoire.size()) {
            memoire.add(0);
        }
        currentCellPointer += i;
        memoire.get(currentCellPointer);
        System.out.println("Memory pointer : " + currentCellPointer);

    }

    @Override
    public String toString() {
        return memoire.toString();
    }
}