package mcga.brainfuck.instructions;


import mcga.brainfuck.Brainfuck;
import mcga.brainfuck.Metrics;
import mcga.brainfuck.exceptions.InvalidValueException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class defining the action corresponding to the reading of a byte in the file specified by the -i argument.
 * This class, as well as the Output class, extends DataIO, as they both permit to interact with the Brainf*ck
 * program.
 * @author Team Make Coding Great Again
 */
public class Input extends DataIO {
    public static InputStream stream;

    /**
     * Default constructor
     */
    public Input() {
    }

    /**
     * Overrides the method defined in the Instruction interface to execute the input action.
     * @throws InvalidValueException
     */
    public void interpret() throws InvalidValueException {
        Metrics.setDataWrite(Metrics.getDataWrite() + 1);
        try {
            int c = stream.read();
            Brainfuck.getMemory().clearCurrentCell();
            Brainfuck.getMemory().addCurrentCellValue(c);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

}
