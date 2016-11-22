package mcga.brainfuck.instructions;

import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.Metrics;

import java.io.PrintStream;

/**
 * Class defining the action corresponding to the writing of the current cell value in the file
 * specified by the -o argument. This class, as well as the Output class, extends DataIO, as they
 * both permit to interact with the Brainf*ck program.
 */
public class Output extends DataIO {
    /**
     * Constructor defining System.out as the output stream
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
