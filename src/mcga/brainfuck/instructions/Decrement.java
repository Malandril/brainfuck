package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.exceptions.InvalidValueException;

import java.util.Arrays;
import java.util.List;

/**
 * Class defining the action corresponding to the decrementation of the value of a cell.
 * This class, as well as the Increment class, extends Operation, as they both define a
 * modification of a cell value in a Brainf*ck program.
 */
public class Decrement extends Operation {

    /**
     * Overrides the method defined in the Instruction interface to execute the decrement action.
     * @throws InvalidValueException
     */
    static List<String> identifiers = Arrays.asList("DECR", "-", "FFFF00");

    @Override
    public void interpret() throws InvalidValueException {
        super.interpret();
        decrementation();
    }

    /**
     * Defines the action of a decrement instruction.
     * @throws InvalidValueException
     */
    private void decrementation() throws InvalidValueException {
        Brainfuck.getMemory().addCurrentCellValue(-1);
    }

}
