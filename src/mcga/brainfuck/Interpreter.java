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
    // TODO: 30/09/2016 changer ce commentaire
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
            if (isUppercase(str)) {
                str+=scanner.nextLine();
            }
            InstructionFactory.createInstruction(str);
        }
    }



    /**
     * Detects if a str of is a str of short or long instructions.
     * A long instruction is composed of uppercase letters.
     * We just need to test if the first character is a letter.
     * @param str Line to test
     * @return true if long instructions, false if short instructions
     */

    public boolean isUppercase(String str) {
        char firstChar = str.charAt(0);
        return (Character.getType(firstChar) == Character.UPPERCASE_LETTER);
    }


    /**
     * Determines the operation to call depending of the instruction.
     * @param instr Instruction
     */
    // TODO: 05/10/2016 changer return


    /**
     * Prints on the standard output the shortened representation of the program given as input.
     */
    public void rewriteFile() {
        Scanner scanner = new Scanner(this.stream);
        String str;
        String strConverted = "";
        while (scanner.hasNext()) {
            str = scanner.nextLine();
            if (isUppercase(str)) {
                strConverted += longToShort(str);
            } else {
                strConverted += str;
            }
        }
        System.out.println(strConverted);
    }

    /**
     * Converts a long keyword to its shortened representation.
     * @param str Long keyword to convert
     * @return Shortened representation
     */
    // TODO: 01/10/2016 change default return
    public String longToShort(String str) {
        switch (str) {
            case "INCR":
                return "+";
            case "DECR":
                return "-";
            case "LEFT":
                return "<";
            case "RIGHT":
                return ">";
            default:
                return "?";
        }
    }
}
