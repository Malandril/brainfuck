package mcga.brainfuck;

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
            Instructions instr = Instructions.hasInstruction(str);
            isolCommand(str);
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

    public void isolCommand(String str){
        String tmp;
        for(int i=0 ; i<str.length() ; i++){
            System.out.println(str.charAt(i));
            tmp += str.charAt(i);
        }
    }
}
