package mcga.brainfuck;

import com.sun.org.apache.bcel.internal.generic.Instruction;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by user on 28/09/2016.
 */
public class Interpreter {

    private InputStream stream;

    public Interpreter(InputStream stream) {
        this.stream = stream;
    }

    public Interpreter() {
        this(System.in);
    }

    public void readFile() {
        Scanner scanner = new Scanner(this.stream);
        String str;

        while (scanner.hasNext()) {
            str = scanner.nextLine();
            if (readLine(str)) {
                Instructions instr = Instructions.hasInstruction(str);
                instrAction(instr);
            } else {
                isolCommand(str);
            }
        }
    }

    public void isolCommand(String str) {
        for (int i = 0 ; i < str.length() ; i++) {
            Instructions strInstr = Instructions.hasInstruction(Character.toString(str.charAt(i)));
            instrAction(strInstr);
            System.out.println(str.charAt(i));
        }
    }

    /**
     * Detects if a line of is a line of short or long instructions.
     * A long instruction is composed of letters. We just need to test if the first character is a letter.
     * @param line
     * @return true if long instructions, false if short instructions
     */
    public boolean readLine(String line) {
        char firstChar = line.charAt(0);
        return (Character.getType(firstChar) == Character.UPPERCASE_LETTER);

    }

    public void instrAction(Instructions instr) {
        if (instr != null) {
            switch (instr) {
                case INCR:
                    new Operation().incrementation();
                    break;
                case DECR:
                    new Operation().decrementation();
                    break;
                case LEFT:
                    new Operation().moveL();
                    break;
                case RIGHT:
                    new Operation().moveR();
                    break;
                default:
                    System.out.println("looooooool");
                    break;
            }
        }
    }
}
