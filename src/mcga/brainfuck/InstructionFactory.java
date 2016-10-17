package mcga.brainfuck;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 28/09/2016.
 */
public enum InstructionFactory {
    INCR("INCR", "+","FFFFFF"),
    DECR("DECR", "-","4B0082"),
    LEFT("LEFT", "<","9400D3"),
    RIGHT("RIGHT", ">","0000FF"),
    JUMP("JUMP", "[","00FF00"),
    BACK("BACK", "]","FFFF00"),
    IN("IN", ",","FF7F00"),
    OUT("OUT", ".","FF0000");


    private static final int LONG_SYNTAX_INDEX = 0;
    private static final int SHORT_SYNTAX_INDEX = 1;
    public static final int BITMAP_COLOR_INDEX=2;
    List<String> identifiers;

    InstructionFactory(String... names) {
        this.identifiers = Arrays.asList(names);
    }

    public static String getShortSyntax(String longStr) throws InvalidInstructionException {
        return hasInstruction(longStr).identifiers.get(SHORT_SYNTAX_INDEX);
    }


    public static InstructionFactory hasInstruction(String str) throws InvalidInstructionException {
        for (InstructionFactory instructionFactory : InstructionFactory.values()) {
            if (instructionFactory.identifiers.contains(str)) {
                return instructionFactory;
            }
        }
        throw new InvalidInstructionException(str);

    }

    public static Instruction getInstruction(String s) throws InvalidInstructionException {
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
                instruction = new Input();
                break;
            case OUT:
                instruction = new Output();
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
