package mcga.brainfuck;

import java.util.Stack;

/**
 * Class defining the action corresponding to the beginning of a loop.
 * This class, as well as the Back class, extends Loop, as they both define a loop in a Brainf*ck program.
 */
public class Jump extends Loop {

    private static Stack<Jump> jumpIndexStack = new Stack<>();

    public Jump() {
        index = interpreter.getIndex();
        jumpIndexStack.add(this);
    }

    public static Jump popJumpStack() {
        return jumpIndexStack.pop();
    }

    /**
     * Overrides the method defined in the Instruction interface to execute the jump action.
     *
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
        if (Brainfuck.memory.getCurrentCellValue() == 0) {
            interpreter.ignoredUntilIndex = boundLoopIndex;
        }
    }
}
