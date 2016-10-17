package mcga.brainfuck;

import java.io.InputStream;
import java.util.Scanner;

/**
 * The Interpreter class contains the interpreter.
 * Its objective is to read the file containing the Brainfuck code and execute the instructions.
 */
public abstract class Parser {

    protected InputStream stream;

    /**
     * In case a file is specified in the launching command, this constructor is called.
     *
     * @param stream Stream from file
     */
    public Parser(InputStream stream) {
        this.stream = stream;
    }

    /**
     * Default constructor.
     * By default, the input stream is System.in.
     */
    public Parser() {
        this(System.in);
    }

    /**
     * Reads the file containing the Brainfuck code.
     */
    public void parseFile() {
        Scanner scanner = new Scanner(this.stream);
        String str;
        scanner.useDelimiter("\\s*");
        while (scanner.hasNext()) {
            str = scanner.next();
            if (InstructionFactory.isLongSyntax(str)) {
                str += scanner.nextLine();
            }
            try {
                execute(str);
            } catch (InvalidInstructionException e) {
                System.err.println(e.getMessage());
                System.exit(42);
            }
        }
    }

    public abstract void execute(String str) throws InvalidInstructionException;


}
