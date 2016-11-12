package mcga.brainfuck;

/**
 * Superclass of the Back and Jump classes.
 */
public abstract class Loop implements Instruction {
    int boundLoopIndex;
    int index;
    static Interpreter interpreter = ((Interpreter) Brainfuck.parsers.get(0));

    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Metrics.DATA_READ++;
    }
}
