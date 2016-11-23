package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.processing.Interpreter;

/**
 * Superclass of the Back and Jump classes.
 * @author Team Make Coding Great Again
 */
public abstract class Loop implements Instruction {
    static Interpreter interpreter = (Brainfuck.getInterpreter());
    protected Loop boundLoop;
    int index;

    /**
     * Overrides the method defined in the Instruction interface to increment the metric DATA_READ
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Metrics.setDataRead(Metrics.getDataRead() + 1);
    }
}
