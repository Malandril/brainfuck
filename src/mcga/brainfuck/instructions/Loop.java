package mcga.brainfuck.instructions;

import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * Superclass of the Back and Jump classes.
 *
 * @author Team Make Coding Great Again
 */
public abstract class Loop implements Instruction {
    protected Loop boundLoop;

    /**
     * Overrides the method defined in the Instruction interface to increment the metric DATA_READ
     *
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
    }
}
