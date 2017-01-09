package mcga.brainfuck.instructions;

import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InstructionException;
import mcga.brainfuck.exceptions.InvalidCodeException;
import mcga.brainfuck.exceptions.InvalidValueException;

import static mcga.brainfuck.Brainfuck.getInterpreter;
import static mcga.brainfuck.Brainfuck.getMemory;

/**
 * Class defining the action corresponding to the end of a loop.
 * This class, as well as the Jump class, extends Loop, as they both define a loop in a Brainf*ck program.
 *
 * @author Team Make Coding Great Again
 */
public class Back extends Loop {
    
    private Jump boundLoop;
    
    /**
     * Default constructor of the Back class
     * Sets the size of the Back and the Jump bounded to it
     */
    public Back() throws InvalidCodeException {
        if (Jump.isJumpStackEmpty()) {
            throw new InvalidCodeException();
        }
        boundLoop = Jump.popJumpStack();
        getInterpreter().popInstructions();
        boundLoop.size = getInterpreter().getIndex() - boundLoop.size - 1;
    }
    
    /**
     * Overrides the method defined in the Instruction interface to execute the back action.
     *
     * @throws InvalidValueException
     */
    @Override
    public void interpret() throws InstructionException {
        super.interpret();
        if (getMemory().getCurrentCellValue() != 0) {
            Metrics.incrExecPos(-1 * boundLoop.size);
            getInterpreter().interpretList(boundLoop.jumpInstructions);
        }
    }
    
    
}
