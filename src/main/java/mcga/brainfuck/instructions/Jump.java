package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InstructionException;
import mcga.brainfuck.exceptions.InvalidValueException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static mcga.brainfuck.Brainfuck.getInterpreter;

/**
 * Class defining the action corresponding to the beginning of a loop.
 * This class, as well as the Back class, extends Loop, as they both define a loop in a Brainf*ck program.
 *
 * @author Team Make Coding Great Again
 */
public class Jump extends Loop {
    private static Deque<Jump> jummpStack = new ArrayDeque<>();
    int size;
    List<Instruction> jumpInstructions = new ArrayList<>();
    
    /**
     * Default constructor
     * Sets the size of the Jump and add the Jump to the Jump table
     */
    public Jump() {
        jummpStack.push(this);
        size = getInterpreter().getIndex();
        getInterpreter().pushInstructions(jumpInstructions);
    }
    
    /**
     * Pops the Jump of the Jump table
     *
     * @return Jump popped from the Jump table
     */
    public static Jump popJumpStack() {
        return jummpStack.pop();
    }
    
    public static boolean isJumpStackEmpty() {
        return jummpStack.isEmpty();
    }
    
    public static Jump peekJumpStack() {
        return jummpStack.peek();
    }
    
    /**
     * Overrides the method defined in the Instruction interface to execute the jump action.
     *
     * @throws InvalidValueException
     */
    @Override
    public void interpret() throws InstructionException {
        Metrics.incrDataRead();
        if (Brainfuck.getMemory().getCurrentCellValue() != 0) {
            getInterpreter().interpretList(jumpInstructions);
        } else {
            Metrics.incrExecPos(size);
        }
    }
}
