package mcga.brainfuck;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 28/09/2016.
 */
public enum InstructionFactory {
    INCR("INCR", "+"),
    DECR("DECR", "-"),
    LEFT("LEFT", "<"),
    RIGHT("RIGHT", ">"),
    JUMP("JUMP", "["),
    BACK("BACK", "]"),
    IN("IN",","),
    OUT("OUT",".");


    private static final int LONG_SYNTAX_INDEX = 0;
    private static final int SHORT_SYNTAX_INDEX = 1;
    List<String> identifiers;

    InstructionFactory(String... names) {
        this.identifiers = Arrays.asList(names);
    }

    public static String getShortSyntax(String longStr) {
        return hasInstruction(longStr).identifiers.get(SHORT_SYNTAX_INDEX);
    }


    public static InstructionFactory hasInstruction(String str) {
        for (InstructionFactory instructionFactory : InstructionFactory.values()) {
            if (instructionFactory.identifiers.contains(str)) {
                return instructionFactory;
            }
        }
        throw new InvalidInstructionException(str);

    }

    public static Instruction getInstruction(String s) {
        InstructionFactory inst = hasInstruction(s);
        Instruction instruction = null;
        switch (inst) {
            case INCR:
                instruction = new Increment();
                break;
            case DECR:
                instruction = new Decrement();
                break;
            case LEFT:
                instruction = new Left();
                break;
            case RIGHT:
                instruction = new Right();
                break;
            case JUMP:
                break;
            case BACK:
                break;
            case IN:
                instruction=new Input();
                break;
            case OUT:
                instruction=new Output();
                break;
            default:
                throw new InvalidInstructionException(s);
        }
        return instruction;
    }

    public static boolean isLongSyntax(String str) {
        char firstChar = str.charAt(0);
        return (Character.getType(firstChar) == Character.UPPERCASE_LETTER);
    }
}
