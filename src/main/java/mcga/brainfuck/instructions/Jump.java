package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static mcga.brainfuck.Brainfuck.getInterpreter;

/**
 * Class defining the action corresponding to the beginning of a loop.
 * This class, as well as the Back class, extends Loop, as they both define a loop in a Brainf*ck program.
 *
 * @author Team Make Coding Great Again
 */
public class Jump extends Loop {
    private static Stack<Jump> jumpIndexStack = new Stack<>();
    int size;
    List<Instruction> jumpInstructions = new ArrayList<>();

    /**
     * Default constructor
     * Sets the size of the Jump and add the Jump to the Jump table
     */
    public Jump() {
        jumpIndexStack.add(this);
        size = getInterpreter().index;
        getInterpreter().pushInstructions(jumpInstructions);
    }

    /**
     * Pops the Jump of the Jump table
     *
     * @return Jump popped from the Jump table
     */
    public static Jump popJumpStack() {
        return jumpIndexStack.pop();
    }

    public static boolean isJumpStackEmpty() {
        return jumpIndexStack.isEmpty();
    }

    public static Jump peekJumpStack() {
        return jumpIndexStack.peek();
    }

    /**
     * Overrides the method defined in the Instruction interface to execute the jump action.
     *
     * @throws InvalidValueException
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void interpret() throws InvalidValueException, IndexOutOfBoundsException {
        Metrics.incrDataRead();
        if (Brainfuck.getMemory().getCurrentCellValue() != 0) {
            getInterpreter().interpretList(jumpInstructions);
        } else {
            Metrics.incrExecPos(size);
        }
    }
}
