package mcga.brainfuck.instructions;

import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * Superclass of the Decrement and Increment classes.
 * @author Team Make Coding Great Again
 */
public abstract class Operation implements Instruction {
    /**
     * Overrides the method of the Instruction interface to increment the metric DATA_WRITE
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Metrics.setDataWrite(Metrics.getDataWrite() + 1);
    }
}
