package mcga.brainfuck;

/**
 * This interface defines the method interpret, which is common to the 8 instructions the Brainf*ck
 * language supports.
 */
public interface Instruction {

    /**
     * Defines the actions to execute when the instruction is encountered.
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     */
    void interpret() throws InvalidValueException, IndexOutOfBoundsException;
}
