package mcga.brainfuck;

/**
 * Superclass of the Back and Jump classes.
 */
public abstract class Loop implements Instruction {
    protected int boundLoopIndex;
    protected int index;
}
