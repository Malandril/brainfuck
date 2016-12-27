package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidValueException;

/**
 * Class defining the action corresponding to the end of a loop.
 * This class, as well as the Jump class, extends Loop, as they both define a loop in a Brainf*ck program.
 *
 * @author Team Make Coding Great Again
 */
public class Back extends Loop {

    /**
     * Default constructor of the Back class
     * Sets the index of the Back and the Jump bounded to it
     */
    public Back() {
        if (Jump.isJumpStackEmpty()) {
            throw new InvalidCodeException();
        }
        boundLoop = Jump.popJumpStack();
        Brainfuck.peekInterpreter().popInstructions();
        boundLoop.boundLoop = this;
    }

    /**
     * Overrides the method defined in the Instruction interface to execute the back action.
     *
     * @throws InvalidValueException
     */
    @Override
    public void interpret() throws InvalidValueException {
        super.interpret();
        back();
    }

    /**
     * Defines the actions of a back instruction.
     */
    private void back() throws InvalidValueException {
        if (Brainfuck.getMemory().getCurrentCellValue() != 0) {
            Brainfuck.peekInterpreter().interpretList(((Jump) boundLoop).jumpInstructions);
        }
    }
}
