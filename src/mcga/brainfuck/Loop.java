package mcga.brainfuck;

/**
 * Superclass of the Back and Jump classes.
 */
public abstract class Loop implements Instruction {
    protected int boundLoopIndex;
    protected int index;

    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Metrics.DATA_READ++;
    }
}
