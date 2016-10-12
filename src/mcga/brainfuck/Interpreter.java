package mcga.brainfuck;

import java.io.InputStream;
import java.util.Scanner;

/**
 * The Interpreter class contains the interpreter.
 * Its objective is to read the file containing the Brainfuck code and execute the instructions.
 */
public class Interpreter {

    private InputStream stream;

    /**
     * In case a file is specified in the launching command, this constructor is called.
     * @param stream Stream from file
     */
    public Interpreter(InputStream stream) {
        this.stream = stream;
    }

    /**
     * Default constructor.
     * By default, the input stream is System.in.
     */
    public Interpreter() {
        this(System.in);
    }

    /**
     * Reads the file containing the Brainfuck code.
     */
    public void readFile() {
        Scanner scanner = new Scanner(this.stream);
        String str;
        scanner.useDelimiter("\\s*");
        while (scanner.hasNext()) {
            str = scanner.next();
            if (InstructionFactory.isLongSyntax(str)) {
                str+=scanner.nextLine();
            }
            InstructionFactory.getInstruction(str).interpret();
        }
    }



    /**
     * Prints on the standard output the shortened representation of the program given as input.
     */
    public void rewriteFile() {
        Scanner scanner = new Scanner(this.stream);
        String str;
        String strConverted = "";
        while (scanner.hasNext()) {
            str = scanner.nextLine();
            if (InstructionFactory.isLongSyntax(str)) {
                strConverted += InstructionFactory.getShortSyntax(str);
            } else {
                strConverted += str;
            }
        }
        System.out.println(strConverted);
    }

}
