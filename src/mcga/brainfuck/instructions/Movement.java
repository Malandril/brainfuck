package mcga.brainfuck.instructions;

import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * Superclass of the Left and Right classes.
 */
public abstract class Movement implements Instruction {
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Metrics.setDataMove(Metrics.getDataMove() + 1);
    }
}
