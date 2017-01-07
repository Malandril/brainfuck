package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.Metrics;

/**
 * Class defining the action corresponding to the writing of the current cell value in the file
 * specified by the -o argument. This class, as well as the Output class, extends DataIO, as they
 * both permit to interact with the Brainf*ck program.
 * @author Team Make Coding Great Again
 */
public class Output extends DataIO {
    /**
     * Default constructor
     */
    public Output() {
    }

    /**
     * Overrides the method defined in the Instruction interface to execute the output action.
     */
    public void interpret() {
        Metrics.incrDataRead();
        Brainfuck.getMainOutput().print((char) Brainfuck.getMemory().getCurrentCellValue());
    }

}
