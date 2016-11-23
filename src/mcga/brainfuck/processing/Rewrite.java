package mcga.brainfuck.processing;

import mcga.brainfuck.exceptions.InvalidInstructionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class defining the actions to do when the user wants to convert his long syntax Brainf*ck code in short syntax.
 *
 * @author Team Make Coding Great Again
 */
public class Rewrite extends Parser {


    public Rewrite() {
        super();
    }

    /**
     * Constructor with the name of the file
     *
     * @param fileName String corresponding to the name of the file to translate
     * @throws FileNotFoundException
     * @see Parser#Parser()
     */
    public Rewrite(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**
     * Constructor with the FileInputStream in parameter
     *
     * @param stream Input stream of the Brainf*ck code.
     * @see Parser#Parser()
     */
    public Rewrite(FileInputStream stream) {
        super(stream);
    }

    /**
     * Prints on the standard output the shortened representation of the program given as input.
     *
     * @param str string to convert.
     * @see Parser#execute(String)
     */
    @Override
    public void execute(String str) {
        String strConverted;
        try {
            strConverted = Parser.getShortSyntax(str);
            System.out.print(strConverted);
        } catch (InvalidInstructionException e) {
            System.err.println(e.getMessage());
            System.exit(42);
        }
    }

}
