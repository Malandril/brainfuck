package mcga.brainfuck.instructions;

import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;

/**
 * Superclass of the Left and Right classes.
 * @author Team Make Coding Great Again
 */
public abstract class Movement implements Instruction {
    /**
     * Overrides the method defined in the Instruction interface to increment the metric DATA_MOVE
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     * @throws MyIndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException, MyIndexOutOfBoundsException {
        Metrics.setDataMove(Metrics.getDataMove() + 1);
    }
}
