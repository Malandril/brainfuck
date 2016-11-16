package mcga.brainfuck.instructions;

import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * Superclass of the Decrement and Increment classes.
 */
public abstract class Operation implements Instruction {
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Metrics.setDataWrite(Metrics.getDataWrite() + 1);
    }
}
