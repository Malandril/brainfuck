package mcga.brainfuck.instructions;

import mcga.brainfuck.exceptions.InstructionException;
import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * Superclass of the Back and Jump classes.
 *
 * @author Team Make Coding Great Again
 */
public abstract class Loop implements Instruction {

    /**
     * Overrides the method defined in the Instruction interface to increment the metric DATA_READ
     *
     * @throws InvalidValueException
     */
    @Override
    public void interpret() throws InstructionException {
    }
}
