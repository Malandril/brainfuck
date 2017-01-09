package mcga.brainfuck.instructions;

import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.BrainfuckIndexOutOfBoundsException;
import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * Superclass of the Left and Right classes.
 * @author Team Make Coding Great Again
 */
public abstract class Movement implements Instruction {
    /**
     * Overrides the method defined in the Instruction interface to increment the metric DATA_MOVE
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     * @throws BrainfuckIndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException, BrainfuckIndexOutOfBoundsException {
        Metrics.setDataMove(Metrics.getDataMove() + 1);
    }
}
