package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.exceptions.BrainfuckIndexOutOfBoundsException;
import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * Class defining the action corresponding to the shift of the memory pointer to the right.
 * This class, as well as the Left class, extends Movement, as they both define a shift of the
 * memory pointer in a Brainf*ck program.
 * @author Team Make Coding Great Again
 */
public class Right extends Movement {

    /**
     * Overrides the method defined in the Instruction interface to shift the memory pointer to the right.
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException, BrainfuckIndexOutOfBoundsException {
        super.interpret();
        Brainfuck.getMemory().changeCurrentIndex(1);
    }

}
