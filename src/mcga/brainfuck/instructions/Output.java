package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.Metrics;

import java.io.PrintStream;

/**
 * Class defining the action corresponding to the writing of the current cell value in the file
 * specified by the -o argument. This class, as well as the Output class, extends DataIO, as they
 * both permit to interact with the Brainf*ck program.
 *
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
        Metrics.setDataRead(Metrics.getDataRead() + 1);
        System.out.print((char) Brainfuck.getMemory().getCurrentCellValue());
    }

}
