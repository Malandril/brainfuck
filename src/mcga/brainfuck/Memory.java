package mcga.brainfuck;

/**
 * Created by user on 26/09/2016.
 */
public class Memory {
    int currentIndex = 0;
    private java.util.List<Integer> memoire;

    public Memory() {
        this.memoire = new MyLinkedList<>();
        this.memoire.add(0);
    }


    public int getCurrentIndex() {
        return currentIndex;
    }

    boolean isValidNumber(int i) {
        return i >= 0 && i < 256;
    }

    void addCurrentCellValue(Integer i) throws InvalidValueException {
        int val = memoire.get(currentIndex);
        if (isValidNumber(val + i)) {
            memoire.set(currentIndex, val + i);
            System.out.println("Memory pointer : " + currentIndex + " la valeur vaut : " + memoire.get(currentIndex));
        } else {
            throw new InvalidValueException();
        }

    }

    void changeCurrentPointerValue(Integer i) throws IndexOutOfBoundsException {
        int val = currentIndex;
        if (val + i >= memoire.size()) {
            memoire.add(0);
        }
        currentIndex += i;
        memoire.get(currentIndex);
        System.out.println("Memory pointer : " + currentIndex);

    }

    @Override
    public String toString() {
        return memoire.toString();
    }

    public Integer getCurrentCellValue() {
        return memoire.get(currentIndex);
    }

    public void clearCurrentCell() {
        memoire.set(currentIndex, 0);
    }
}