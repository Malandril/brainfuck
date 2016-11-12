package mcga.brainfuck;

/**
 * Superclass of the Left and Right classes.
 */
public abstract class Movement implements Instruction {
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Metrics.DATA_MOVE++;
    }
}
