package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;

import java.util.Arrays;
import java.util.List;

/**
 * Class defining the action corresponding to the shift of the memory pointer to the right.
 * This class, as well as the Left class, extends Movement, as they both define a shift of the
 * memory pointer in a Brainf*ck program.
 */
public class Right extends Movement {

    /**
     * Overrides the method defined in the Instruction interface to shift the memory pointer to the right.
     *
     * @throws IndexOutOfBoundsException
     */

    static List<String> identifiers = Arrays.asList("RIGHT", ">", "FFFF00");

    @Override
    public void interpret() throws InvalidValueException, MyIndexOutOfBoundsException {
        super.interpret();
        Brainfuck.getMemory().changeCurrentIndex(1);
    }


}
