package mcga.brainfuck;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by user on 28/09/2016.
 */
public class Interpreter {

    private InputStream stream;

    /**
     * Constructor
     * Use argument file
     * @param stream == file
     */
    public Interpreter(InputStream stream) {this.stream = stream;}

    /**
     * Constructor
     * Use STDInput
     */
    public Interpreter() {
        this(System.in);
    }

    public void readFile() {
        Scanner scanner = new Scanner(this.stream);
        String str;
        if (!scanner.hasNext()) { // If
            System.exit(0);
        }
        while (scanner.hasNext()) {
            str = scanner.nextLine();
            Instructions instr = Instructions.hasInstruction(str);
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
                        break;
                }
            } else {
                System.err.println("Error not a valid instruction " + str);
                System.exit(42);
            }
        }

    }
}
