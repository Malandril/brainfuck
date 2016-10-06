package mcga.brainfuck;

/**
 * The Operation class determines the action to execute for each possible operation on the memory.
 */
public class Operation {

    /**
     * Default constructor
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
        Brainfuck.memoire.addCurrentCellValue(- 1);
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