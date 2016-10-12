package mcga.brainfuck;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 28/09/2016.
 */
public enum InstructionFactory {
    INCR("INCR", "+"),
    DECR("DECR", "-"),
    LEFT("LEFT", "<"),
    RIGHT("RIGHT", ">");

    private static final int LONG_SYNTAX_INDEX = 0;
    private static final int SHORT_SYNTAX_INDEX = 1;
    List<String> names;

    InstructionFactory(String... names) {
        this.names = Arrays.asList(names);
    }

    public static String getShortSyntax(String longStr) {
        return hasInstruction(longStr).names.get(SHORT_SYNTAX_INDEX);
    }

    public static InstructionFactory hasInstruction(String str) {
        return Arrays.stream(InstructionFactory.values()).filter(s -> s.names.contains(str)).findFirst().orElse(null);
    }

    public static Instruction getInstruction(String s) {
        InstructionFactory inst = hasInstruction(s);
        Instruction instruction;
        if (inst != null) {
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
                default:
                    instruction = null;
                    break;
            }
        } else {
            throw new IllegalArgumentException(s);
        }
        return instruction;
    }

    public static boolean isLongSyntax(String str) {
        char firstChar = str.charAt(0);
        return (Character.getType(firstChar) == Character.UPPERCASE_LETTER);
    }
}
