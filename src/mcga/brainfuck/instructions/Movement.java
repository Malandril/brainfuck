package mcga.brainfuck.instructions;

import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;

/**
 * Superclass of the Left and Right classes.
 */
public abstract class Movement implements Instruction {
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException, MyIndexOutOfBoundsException {
        Metrics.setDataMove(Metrics.getDataMove() + 1);
    }
}
