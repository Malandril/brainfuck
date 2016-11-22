package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * Class defining the action corresponding to the incrementation of the value of a cell.
 * This class, as well as the Decrement class, extends Operation, as they both define a
 * modification of a cell value in a Brainf*ck program.
 *
 * @author Team Make Coding Great Again
 */
public class Increment extends Operation {

    /**
     * Overrides the method defined in the Instruction interface to execute the increment action.
     * @throws InvalidValueException
     */
    @Override
    public void interpret() throws InvalidValueException {
        super.interpret();
        incrementation();
    }

    /**
     * Defines the action of an increment instruction.
     * @throws InvalidValueException
     */
    private void incrementation() throws InvalidValueException {
        Brainfuck.getMemory().addCurrentCellValue(1);
    }

}
