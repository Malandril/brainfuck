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

        while (scanner.hasNext()) {
            str = scanner.nextLine();
            if (readLine(str)) {
                System.out.println(str);
                Instructions instr = Instructions.hasInstruction(str);
                instrAction(instr);
            } else {
                isolCommand(str);
            }
        }
    }

    /**
     * Takes each character of a String and executes the action which corresponds.
     * This method is called if the line of the file is a line of short instructions.
     * Therefore, each character represents an instruction to execute.
     * @param str String to split
     */
    public void isolCommand(String str) {
        for (int i = 0 ; i < str.length() ; i++) {
            Instructions strInstr = Instructions.hasInstruction(Character.toString(str.charAt(i)));
            instrAction(strInstr);
            System.out.println(str.charAt(i));
        }
    }

    /**
     * Detects if a line of is a line of short or long instructions.
     * A long instruction is composed of uppercase letters.
     * We just need to test if the first character is a letter.
     * @param line Line to test
     * @return true if long instructions, false if short instructions
     */
    public boolean readLine(String line) {
        char firstChar = line.charAt(0);
        return (Character.getType(firstChar) == Character.UPPERCASE_LETTER);

    }

    /**
     * Determines the operation to call depending of the instruction.
     * @param instr Instruction
     */
    // TODO: 05/10/2016 changer return
    public void instrAction(Instructions instr) {
        if (instr != null) {
            switch (instr) {
                case INCR:
                    new Increment().interpret();
                    break;
                case DECR:
                    new Decrement().interpret();
                    break;
                case LEFT:
                    new Left().interpret();
                    break;
                case RIGHT:
                    new Right().interpret();
                    break;
            }
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
            if (readLine(str)) {
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
