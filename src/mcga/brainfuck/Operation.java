package mcga.brainfuck;

/**
 * Superclass of the Decrement and Increment classes.
 */
public abstract class Operation implements Instruction {
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Parser.DATA_WRITE++;
    }
}
