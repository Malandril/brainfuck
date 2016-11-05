package mcga.brainfuck;

import javax.xml.crypto.Data;
import java.io.PrintStream;

/**
 * Class defining the action corresponding to the writing of the current cell value in the file
 * specified by the -o argument. This class, as well as the Output class, extends DataIO, as they
 * both permit to interact with the Brainf*ck program.
 */
public class Output extends DataIO {
    public static PrintStream stream;

    /**
     * Constructor defining System.out as the output stream
     */
    public Output() {
    }

    /**
     * Overrides the method defined in the Instruction interface to execute the output action.
     */
    public void interpret() {
        Parser.DATA_READ++;
        stream.print((char) Brainfuck.memory.getCurrentCellValue());
    }

}
