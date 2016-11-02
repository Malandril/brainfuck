package mcga.brainfuck;


import java.io.IOException;
import java.io.InputStream;

/**
 * Class defining the action corresponding to the reading of a byte in the file specified by the -i argument.
 * This class, as well as the Output class, extends DataIO, as they both permit to interact with the Brainf*ck
 * program.
 */
public class Input extends DataIO {
    static InputStream stream;

    /**
     * Default constructor
     */
    public Input() {
    }

    /**
     * Overrides the method defined in the Instruction interface to execute the input action.
     *
     * @throws InvalidValueException
     */
    public void interpret() throws InvalidValueException {
        try {
            int c = stream.read();
            Brainfuck.memory.clearCurrentCell();
            Brainfuck.memory.addCurrentCellValue(c);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

}
