package mcga.brainfuck.processing;

import mcga.brainfuck.InstructionCreator;
import mcga.brainfuck.exceptions.InvalidInstructionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class defining the actions to do when the user wants to convert his long syntax Brainf*ck code in short syntax.
 * Because this class reads a file, it extends Parser.
 */
public class Rewrite extends Parser {

    public Rewrite(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**

     * Constructor of the class.
     * @param stream Input stream of the Brainf*ck code.
     * @see Parser#Parser()
     */
    public Rewrite(FileInputStream stream) {
        super(stream);
    }

    /**
     * Prints on the standard output the shortened representation of the program given as input.
     * @param str string to convert.
     * @see Parser#execute(String)
     */
    @Override
    public void execute(String str) {
        String strConverted;
        try {
            strConverted = InstructionCreator.getShortSyntax(str);
            System.out.print(strConverted);
        } catch (InvalidInstructionException e) {
            System.err.println(e.getMessage());
            System.exit(42);
        }
    }

}
