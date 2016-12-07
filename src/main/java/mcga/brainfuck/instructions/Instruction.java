package mcga.brainfuck.instructions;

import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;

/**
 * This interface defines the method interpret, which is common to the 8 instructions the Brainf*ck
 * language supports.
 * @author Team Make Coding Great Again
 */
public interface Instruction {

    /**
     * Defines the actions to execute when the instruction is encountered.
     * @throws InvalidValueException
     * @throws MyIndexOutOfBoundsException
     */
    void interpret() throws InvalidValueException, MyIndexOutOfBoundsException;
}
