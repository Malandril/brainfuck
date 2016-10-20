package mcga.brainfuck;

import java.io.InputStream;

/**
 * Class defining the actions to do when the user wants to convert his long syntax Brainf*ck code in short syntax.
 * Because this class reads a file, it extends Parser.
 */
public class Rewrite extends Parser {

    /**
     * Constructor of the class.
     * @param stream Input stream of the Brainf*ck code.
     * @see Parser#Parser()
     */
    public Rewrite(InputStream stream) {
        super(stream);
    }

    /**
     * Prints on the standard output the shortened representation of the program given as input.
     * @param str string to convert.
     * @see Parser#execute(String)
     */
    @Override
    public void execute(String str) {
        String strConverted = null;
        try {
            strConverted = InstructionFactory.getShortSyntax(str);
            System.out.print(strConverted);
        } catch (InvalidInstructionException e) {
            System.err.println(e.getMessage());
            System.exit(42);
        }
    }

}
