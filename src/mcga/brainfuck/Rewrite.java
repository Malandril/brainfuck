package mcga.brainfuck;

import java.io.InputStream;

/**
 * Created by user on 12/10/2016.
 */
public class Rewrite extends Parser {


    public Rewrite(InputStream stream) {
        super(stream);
    }

    public Rewrite() {
        super();
    }

    /**
     * Prints on the standard output the shortened representation of the program given as input.
     */
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
