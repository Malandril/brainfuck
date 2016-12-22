package mcga.brainfuck;

import mcga.brainfuck.exceptions.InvalidInstructionException;
import mcga.brainfuck.instructions.*;
import mcga.brainfuck.processing.Parser;

import java.util.Arrays;
import java.util.List;

/**
 * This enum links the keywords in the Brainf*ck code to the instruction they correspond to.
 *
 * @author Team Make Coding Great Again
 */
public enum InstructionCreator {
    INCR("INCR", "+", "FFFFFF"),
    DECR("DECR", "-", "4B0082"),
    LEFT("LEFT", "<", "9400D3"),
    RIGHT("RIGHT", ">", "0000FF"),
    IN("IN", ",", "FFFF00"),
    OUT("OUT", ".", "00FF00"),
    JUMP("JUMP", "[", "FF7F00"),
    BACK("BACK", "]", "FF0000");


    public static final int SHORT_SYNTAX_INDEX = 1;
    public static final int BITMAP_COLOR_INDEX = 2;
    private List<String> identifiers;

    /**
     * Constructor of the enum
     *
     * @param names List<String> containing the possible syntax for each instruction.
     */
    InstructionCreator(String... names) {
        this.identifiers = Arrays.asList(names);
    }

    /**
     * Searches for the short syntax representation corresponding to a long syntax representation.
     *
     * @param longStr String corresponding to the long syntax of an instruction.
     * @return String corresponding to the short representation of the instruction.
     * @throws InvalidInstructionException
     */
    public static String getShortSyntax(String longStr) throws InvalidInstructionException {
        return hasInstruction(longStr).identifiers.get(SHORT_SYNTAX_INDEX);
    }

    /**
     * Searches for the bitmap color index corresponding to a long or short syntax representation.
     *
     * @param syntax String corresponding to the short or long syntax of an instruction.
     * @return Bitmap color index corresponding to the short representation of the instruction.
     * @throws InvalidInstructionException
     */
    public static String getBitmapColorIndex(String syntax) throws InvalidInstructionException {
        InstructionCreator inst = hasInstruction(syntax);
        if (inst != null) {
            return "#" + inst.identifiers.get(BITMAP_COLOR_INDEX);
        }
        throw new InvalidInstructionException(syntax);
    }

    /**
     * Finds the instruction (if it exists) corresponding to the String in parameter.
     *
     * @param str String to check.
     * @return Instruction corresponding
     * @throws InvalidInstructionException
     */
    public static InstructionCreator hasInstruction(String str) {
        for (InstructionCreator instructionCreator : InstructionCreator.values()) {
            if (instructionCreator.identifiers.contains(str)) {
                return instructionCreator;
            }
        }
        return null;

    }

    /**
     * Creates an Instruction object and, for each possible instruction, creates the object corresponding.
     *
     * @param s String to try to convert to an instruction.
     * @return Instruction corresponding to the String in parameter.
     * @throws InvalidInstructionException
     */
    public static Instruction createInstruction(String s) throws InvalidInstructionException {
        InstructionCreator inst = hasInstruction(s);
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
                case JUMP:
                    instruction = new Jump();
                    break;
                case BACK:
                    instruction = new Back();
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
        } else {
            String code = Parser.procedures.get(s);
            if (code != null) {
                return new Procedure(s, code);
            }
        }
        throw new InvalidInstructionException(s);
    }


    public String getIdentifier(int i) {
        return this.identifiers.get(i);
    }


}
