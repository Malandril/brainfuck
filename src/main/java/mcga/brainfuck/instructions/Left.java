package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.exceptions.InvalidValueException;
import mcga.brainfuck.exceptions.MyIndexOutOfBoundsException;

/**
 * Class defining the action corresponding to the shift of the memory pointer to the left.
 * This class, as well as the Right class, extends Movement, as they both define a shift of the
 * memory pointer in a Brainf*ck program.
 * @author Team Make Coding Great Again
 */
public class Left extends Movement {

    /**
     * Overrides the method defined in the Instruction interface to shift the memory pointer to the left.
     * @throws InvalidValueException
     */
    @Override
    public void interpret() throws InvalidValueException, MyIndexOutOfBoundsException {
        super.interpret();
        Brainfuck.getMemory().changeCurrentIndex(- 1);
    }


}
