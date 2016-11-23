package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.exceptions.InvalidValueException;

import java.util.Stack;

/**
 * Class defining the action corresponding to the beginning of a loop.
 * This class, as well as the Back class, extends Loop, as they both define a loop in a Brainf*ck program.
 * @author Team Make Coding Great Again
 */
public class Jump extends Loop {

    private static Stack <Jump> jumpIndexStack = new Stack <>();

    /**
     * Default constructor
     * Sets the index of the Jump and add the Jump to the Jump table
     */
    public Jump() {
        index = interpreter.getIndex();
        jumpIndexStack.add(this);
    }

    /**
     * Pops the Jump of the Jump table
     * @return Jump popped from the Jump table
     */
    public static Jump popJumpStack() {
        return jumpIndexStack.pop();
    }

    public static boolean isJumpStackEmpty() {
        return jumpIndexStack.isEmpty();
    }

    /**
     * Overrides the method defined in the Instruction interface to execute the jump action.
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        super.interpret();
        jump();
    }

    /**
     * Defines the actions of a jump instruction.
     */

    private void jump() {
        if (Brainfuck.getMemory().getCurrentCellValue() == 0) {
            interpreter.ignoredUntilIndex = boundLoopIndex;
        }
    }
}
