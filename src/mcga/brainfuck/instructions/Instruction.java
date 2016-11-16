package mcga.brainfuck.instructions;

import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * This interface defines the method interpret, which is common to the 8 instructions the Brainf*ck
 * language supports.
 */
public interface Instruction {

    /**
     * Defines the actions to execute when the instruction is encountered.
     * @throws InvalidValueException
     */
    void interpret() throws InvalidValueException;
}
