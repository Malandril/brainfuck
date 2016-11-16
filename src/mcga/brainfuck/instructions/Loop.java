package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.processing.Interpreter;

/**
 * Superclass of the Back and Jump classes.
 */
public abstract class Loop implements Instruction {
    static Interpreter interpreter = (Brainfuck.getInterpreter());
    int boundLoopIndex;
    int index;

    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Metrics.setDataRead(Metrics.getDataRead() + 1);
    }
}
