package mcga.brainfuck;

/**
 * Created by user on 26/09/2016.
 *
 */
public class Operation {

    /**
     * Constructor
     */
    public Operation() {
    }

    /**
     * Increments the memory by 1
     */
    public void incrementation() {
        Brainfuck.memoire.addCurrentCellValue(1);
    }

    /**
     * Decrements the memory by 1
     */
    public void decrementation() {
        Brainfuck.memoire.addCurrentCellValue(-1);
    }

    /**
     * Move the memory pointer to the right
     */
    public void moveR() {
        Brainfuck.memoire.changeCurrentIndex(1);
    }

    /**
     * Move the memory pointer to the left
     */
    public void moveL() {
        Brainfuck.memoire.changeCurrentIndex(-1);
    }

}
